module.exports = app => {
    const findAll = (req, res) => {
        app.db('tb_categories')
            .orderBy('name')
            .then(categories => res.json(categories))
            .catch(err => res.status(400).json(err))
    }

    const findById = (req, res) => {
        app.db('tb_categories')
            .where({ id: req.params.id })
            .then(categories => res.json(categories))
            .catch(err => res.status(400).json(err))
    }

    const save = (req, res) => {
        if (!req.body.name.trim()) {
            return res.status(400).send('O nome da categoria é um campo obrigatório')
        }

        app.db('tb_categories')
            .insert(req.body)
            .then(_ => res.status(204).send())
            .catch(err => res.status(400).json(err))
    }

    const remove = (req, res) => {
        app.db('tb_categories')
            .where({ id: req.params.id })
            .del()
            .then(rowsDeleted => {
                if (rowsDeleted > 0) {
                    res.status(204).send()
                } else {
                    const msg = `Não foi encontrada a categoria com id ${req.params.id}.`
                    res.status(400).send(msg)
                }
            })
            .catch(err => res.status(400).json(err))
    }

    const update = (req, res) => {
        app.db('tb_categories')
            .where({ id: req.params.id })
            .update({ name: req.body.name })
            .then(_ => res.status(204).send())
            .catch(err => res.status(400).json(err))
    }

    return { findAll, findById, save, remove, update }
}