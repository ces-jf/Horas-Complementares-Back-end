module.exports = app => {
    const findAll = (req, res) => {
        app.db('tb_usertypes')
            .orderBy('name')
            .then(usertypes => res.json(usertypes))
            .catch(err => res.status(400).json(err))
    }

    const findById = (req, res) => {
        app.db('tb_usertypes')
            .where({ id: req.params.id })
            .then(usertypes => res.json(usertypes))
            .catch(err => res.status(400).json(err))
    }

    const save = (req, res) => {
        if (!req.body.name.trim()) {
            return res.status(400).send('O nome do Tipo de Usuário é um campo obrigatório')
        }

        app.db('tb_usertypes')
            .insert(req.body)
            .then(_ => res.status(204).send())
            .catch(err => res.status(400).json(err))
    }

    const remove = (req, res) => {
        app.db('tb_usertypes')
            .where({ id: req.params.id })
            .del()
            .then(rowsDeleted => {
                if (rowsDeleted > 0) {
                    res.status(204).send()
                } else {
                    const msg = `Não foi encontrado o Tipo de Usuário com id ${req.params.id}.`
                    res.status(400).send(msg)
                }
            })
            .catch(err => res.status(400).json(err))
    }

    const update = (req, res) => {
        app.db('tb_usertypes')
            .where({ id: req.params.id })
            .update({ name: req.body.name, profile: req.body.profile })
            .then(_ => res.status(204).send())
            .catch(err => res.status(400).json(err))
    }

    return { findAll, findById, save, remove, update }
}