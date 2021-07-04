module.exports = app => {
    const findAll = (req, res) => {
        app.db('tb_campus')
            .orderBy('name')
            .then(campus => res.json(campus))
            .catch(err => res.status(400).json(err))
    }

    const findById = (req, res) => {
        app.db('tb_campus')
            .where({ id: req.params.id })
            .then(campus => res.json(campus))
            .catch(err => res.status(400).json(err))
    }

    const save = (req, res) => {
        if (!req.body.name.trim()) {
            return res.status(400).send('O nome do Campus é um campo obrigatório')
        }

        app.db('tb_campus')
            .insert(req.body)
            .then(_ => res.status(204).send())
            .catch(err => res.status(400).json(err))
    }

    const remove = (req, res) => {
        app.db('tb_campus')
            .where({ id: req.params.id })
            .del()
            .then(rowsDeleted => {
                if (rowsDeleted > 0) {
                    res.status(204).send()
                } else {
                    const msg = `Não foi encontrado o Campus com id ${req.params.id}.`
                    res.status(400).send(msg)
                }
            })
            .catch(err => res.status(400).json(err))
    }

    const update = (req, res) => {
        app.db('tb_campus')
            .where({ id: req.params.id })
            .update({ name: req.body.name })
            .then(_ => res.status(204).send())
            .catch(err => res.status(400).json(err))
    }

    return { findAll, findById, save, remove, update }
}