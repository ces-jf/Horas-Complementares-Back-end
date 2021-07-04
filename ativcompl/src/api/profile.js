module.exports = app => {
    const findAll = (req, res) => {
        app.db('tb_users')
            .where({ id: req.user.id })
            .then(user => res.json(user))
            .catch(err => res.status(400).json(err))
    }

    const findMail = (req, res) => {
        app.db('tb_users')
            .where({ id: req.user.id })
            .first()
            .select('id', 'name', 'email')
            .then(user => res.json(user))
            .catch(err => res.status(400).json(err))
    }

    const find = (req, res) => {
        app.db('tb_users')
            .where({ id: req.params.id })
            .then(user => res.json(user))
            .catch(err => res.status(400).json(err))
    }

    const findUserType = (req, res) => {
        const usertype = 1
        app.db('tb_users as u')
            .join('tb_users_courses as uc', 'u.id', '=', 'uc.userId')
            .join('tb_courses as c', 'uc.courseId', '=', 'c.id')
            .select('uc.id', 'u.id as userId', 'u.name', 'c.id as courseId', 'c.name as course')
            .where({ usertypeId: usertype })
            .orderBy('u.name')
            .then(user => res.json(user))
            .catch(err => res.status(400).json(err))
    }

    const update = (req, res) => {
        app.db('tb_users')
            .where({ id: req.params.id })
            .update({
                name: req.body.name,
                registration: req.body.registration,
                email: req.body.email,
                password: req.body.password
            })
            .then(_ => res.status(204).send())
            .catch(err => res.status(400).json(err))
    }

    const updatePassword = (req, res) => {
        app.db('tb_users')
            .where({ id: req.params.id })
            .update({ password: req.body.password })
            .then(_ => res.status(204).send())
            .catch(err => res.status(400).json(err))
    }

    return { find, findAll, findMail, findUserType, update, updatePassword }
}