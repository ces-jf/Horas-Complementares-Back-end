const moment = require('moment')

module.exports = app => {
    const findWorkloadCompleted = (req, res) => {
        app.db('tb_activities')
            .where({ userId: req.user.id })
            .sum('workloadValidated')
            .then(activities => res.json(activities))
            .catch(err => res.status(400).json(err))
    }

    const findWorkloadValidatedMail = (req, res) => {
        app.db('tb_activities')
            .where({ userId: req.user.id, courseId: req.params.courseId })
            .sum('workloadValidated')
            .then(activities => res.json(activities))
            .catch(err => res.status(400).json(err))
    }

    const findWorkloadValitedByCourse = (req, res) => {
        app.db('tb_activities')
            .where({ userId: req.params.userId, courseId: req.params.courseId })
            .sum('workloadValidated')
            .then(activities => res.json(activities))
            .catch(err => res.status(400).json(err))
    }

    const findUser = (req, res) => {
        app.db('tb_activities')
            .where({ userId: req.params.userId, courseId: req.params.courseId })
            .orderBy('start')
            .then(activities => res.json(activities))
            .catch(err => res.status(400).json(err))
    }

    const findMail = (req, res) => {
        app.db('tb_activities')
            .where({ userId: req.user.id, courseId: req.params.courseId })
            .orderBy('end')
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
            .where({ id: req.params.id })
            .first()
            .then(activity => res.json(activity))
            .catch(err => res.status(400).json(err))
    }

    const save = async (req, res) => {
        if (!req.body.name.trim()) {
            return res.status(400).send('O nome da atividade é um campo obrigatório')
        }

        req.body.userId = req.user.id
        req.body.workloadValidated = 0

        const result = await app.db('tb_activities')
            .insert({
                name: req.body.name,
                institution: req.body.institution,
                start: req.body.start,
                end: req.body.end,
                workload: req.body.workload,
                workloadValidated: req.body.workloadValidated,
                completed: req.body.completed,
                certificate: req.body.certificate,
                categoryId: req.body.categoryId,
                courseId: req.body.courseId,
                userId: req.body.userId
            }, '*')
            .then(result => {
                res.status(204).json(result)
                return result
            })
            .catch(err => res.status(400).json(err))

        const activityId = await result[0].id

        await app.db('tb_notifications')
            .insert({
                userSendId: req.body.userId,
                userRecipientId: req.body.userRecipientId,
                activityId: activityId,
                message: req.body.message,
                read: req.body.read
            })
            .then(_ => res.status(204))
            .catch(err => res.status(400)/* .json(err) */)
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

    const updateActivityValuation = (req, res, workloadValidated, completed) => {
        app.db('tb_activities')
            .where({ id: req.params.id })
            .update({
                workloadValidated,
                completed
            })
            .then(_ => res.status(204).send())
            .catch(err => res.status(400).json(err))
    }

    const valuationActivity = (req, res) => {
        app.db('tb_activities')
            .where({ id: req.params.id })
            .first()
            .then(activity => {
                if (!activity) {
                    const msg = `Atividade com id ${req.params.id} não encontrada.`
                    return res.status(400).send(msg)
                }

                updateActivityValuation(req, res, req.body.workloadValidated, req.body.completed)
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
        findWorkloadValitedByCourse,
        findUser,
        findAllDeadline,
        findMail,
        findWorkloadValidatedMail,
        save,
        remove,
        toggleActivity,
        certificateActivity,
        valuationActivity,
        findAllTeste,
        saveTeste
    }
}