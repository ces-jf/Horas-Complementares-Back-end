module.exports = app => {
    const findAll = (req, res) => {
        app.db('tb_users_courses')
            .where({ userId: req.user.id })
            .orderBy('id')
            .then(userCourse => res.json(userCourse))
            .catch(err => res.status(400).json(err))
    }

    const findById = (req, res) => {
        app.db('tb_users_courses')
            .where({ id: req.params.id, userId: req.user.id })
            .then(userCourse => res.json(userCourse))
            .catch(err => res.status(400).json(err))
    }

    const findUserCourse = (req, res) => {
        app.db('tb_users_courses as uc')
            .join('tb_courses as c', 'uc.courseId', '=', 'c.id')
            .select('uc.id', 'c.name', 'c.limit')
            .where({ userId: req.user.id })
            .then(userCourse => res.json(userCourse))
            .catch(err => res.status(400).json(err))
    }

    const findMail = (req, res) => {
        app.db('tb_users_courses as uc')
            .join('tb_courses as c', 'uc.courseId', '=', 'c.id')
            .select('uc.id', 'c.name', 'c.workload')
            .where({ courseId: req.params.courseId, userId: req.user.id })
            .first()
            .then(userCourse => res.json(userCourse))
            .catch(err => res.status(400).json(err))
    }

    const save = (req, res) => {

        req.body.userId = req.user.id

        app.db('tb_users_courses')
            .insert(req.body)
            .then(_ => res.status(204).send())
            .catch(err => res.status(400).json(err))
    }

    const saveIni = (req, res) => {

        app.db('tb_users_courses')
            .insert(req.body)
            .then(_ => res.status(204).send())
            .catch(err => res.status(400).json(err))
    }

    const remove = (req, res) => {
        app.db('tb_users_courses')
            .where({ id: req.params.id, userId: req.user.id })
            .del()
            .then(rowsDeleted => {
                if (rowsDeleted > 0) {
                    res.status(204).send()
                } else {
                    const msg = `Não foi encontrada o curso com id ${req.params.id}.`
                    res.status(400).send(msg)
                }
            })
            .catch(err => res.status(400).json(err))
    }

    const updateCourse = (req, res) => {
        app.db('tb_users_courses')
        .where({ id: req.params.id, userId: req.user.id })
            .update({ courseId: req.body.courseId })
            .then(_ => res.status(204).send())
            .catch(err => res.status(400).json(err))
    }

    const updateUserType = (req, res) => {
        app.db('tb_users_courses')
            .where({ id: req.params.id })
            .update({ usertypeId: req.body.usertypeId })
            .then(_ => res.status(204).send())
            .catch(err => res.status(400).json(err))
    }

    return { findAll, findById, findUserCourse, findMail, save, saveIni, remove, updateCourse, updateUserType }
}