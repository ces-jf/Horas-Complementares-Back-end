module.exports = app => {
    const findAll = (req, res) => {
        app.db('tb_users')
            .where({ id: req.user.id })
            .then(user => res.json(user))
            .catch(err => res.status(400).json(err))
    }

    const find = (req, res) => {
        app.db('tb_users')
            .where({ id: req.params.id })
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

    return { find, findAll, update, updatePassword }
}