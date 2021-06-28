const moment = require('moment')

module.exports = app => {
    const findWorkloadCompleted = (req, res) => {
        app.db('tb_activities')
            .where({ userId: req.user.id })
            .sum('workload')
            .then(activities => res.json(activities))
            .catch(err => res.status(400).json(err))
    }

    const findAllDeadline = (req, res) => {
        const date = req.query.date ? req.query.date
            : moment().endOf('day').toDate()

        app.db('tb_activities')
            .where({ userId: req.user.id })
            .where('start', '<=', date)
            .orderBy('start')
            .then(activities => res.json(activities))
            .catch(err => res.status(400).json(err))
    }

    const findAll = (req, res) => {
        app.db('tb_activities')
            .where({ userId: req.user.id })
            .orderBy('start')
            .then(activities => res.json(activities))
            .catch(err => res.status(400).json(err))
    }

    const findById = (req, res) => {
        app.db('tb_activities')
            .where({ id: req.params.id, userId: req.user.id })
            .then(categories => res.json(categories))
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

    const updateActivityCompleted = (req, res, completed) => {
        app.db('tb_activities')
            .where({ id: req.params.id, userId: req.user.id })
            .update({ completed })
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

                const completed = activity.completed ? false : true
                updateActivityCompleted(req, res, completed)
            })
            .catch(err => res.status(400).json(err))
    }

    const updateActivityCertificate = (req, res, certificate) => {
        app.db('tb_activities')
            .where({ id: req.params.id, userId: req.user.id })
            .update({ certificate })
            .then(_ => res.status(204).send())
            .catch(err => res.status(400).json(err))
    }

    const certificateActivity = (req, res) => {
        app.db('tb_activities')
            .where({ id: req.params.id, userId: req.user.id })
            .first()
            .then(activity => {
                if (!activity) {
                    const msg = `Atividade com id ${req.params.id} não encontrada.`
                    return res.status(400).send(msg)
                }

                updateActivityCertificate(req, res, req.body.certificate)
            })
            .catch(err => res.status(400).json(err))
    }

    const findAllTeste = (req, res) => {
        app.db('tb_activities')
            .orderBy('start')
            .then(activities => res.json(activities))
            .catch(err => res.status(400).json(err))
    }

    const saveTeste = (req, res) => {
        if (!req.body.name.trim()) {
            return res.status(400).send('O nome da atividade é um campo obrigatório')
        }

        req.body.userId = 1

        app.db('tb_activities')
            .insert(req.body)
            .then(_ => res.status(204).send())
            .catch(err => res.status(400).json(err))
    }

    return { 
        findAll, 
        findById, 
        findWorkloadCompleted, 
        findAllDeadline, 
        save, 
        remove, 
        toggleActivity, 
        certificateActivity, 
        findAllTeste, 
        saveTeste 
    }
}