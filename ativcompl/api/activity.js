const moment = require('moment')

module.exports = app => {
    const getActivities = (req, res) => {
        const date = req.query.date ? req.query.date
            : moment().endOf('day').toDate()

        app.db('tb_activities')
            .where({ userId: req.user.id })
            .where('start', '<=', date)
            .orderBy('start')
            .then(activities => res.json(activities))
            .catch(err => res.status(400).json(err))
    }

    const save = (req, res) => {
        if (!req.body.name.trim()) {
            return res.status(400).send('O nome da atividade é um campo obrigatório')
        }

        req.body.userId = req.user.id

        app.db('tb_activities')
            .insert(req.body)
            .then(_ => res.status(204).send())
            .catch(err => res.status(400).json(err))
    }

    const remove = (req, res) => {
        app.db('tb_activities')
            .where({ id: req.params.id, userId: req.user.id })
            .del()
            .then(rowsDeleted => {
                if (rowsDeleted > 0) {
                    res.status(204).send()
                } else {
                    const msg = `Não foi encontrada atividade com id ${req.params.id}.`
                    res.status(400).send(msg)
                }
            })
            .catch(err => res.status(400).json(err))
    }

    const updateActivityClosed = (req, res, closed) => {
        app.db('tb_activities')
            .where({ id: req.params.id, userId: req.user.id })
            .update({ closed })
            .then(_ => res.status(204).send())
            .catch(err => res.status(400).json(err))
    }

    const toggleActivity = (req, res) => {
        app.db('tb_activities')
            .where({ id: req.params.id, userId: req.user.id })
            .first()
            .then(activity => {
                if (!activity) {
                    const msg = `Atividade com id ${req.params.id} não encontrada.`
                    return res.status(400).send(msg)
                }

                const closed = activity.closed ? null : true
                updateActivityClosed(req, res, closed)
            })
            .catch(err => res.status(400).json(err))
    }

    return { getActivities, save, remove, toggleActivity }
}