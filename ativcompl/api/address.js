module.exports = app => {
    const findAll = (req, res) => {
        app.db('tb_addresses')
            .where({ userId: req.user.id })
            .orderBy('street')
            .then(addresses => res.json(addresses))
            .catch(err => res.status(400).json(err))
    }

    const findById = (req, res) => {
        app.db('tb_addresses')
            .where({ id: req.params.id, userId: req.user.id })
            .then(addresses => res.json(addresses))
            .catch(err => res.status(400).json(err))
    }

    const findByUserId = (req, res) => {
        app.db('tb_addresses')
            .where({ userId: req.user.id })
            .then(addresses => res.json(addresses))
            .catch(err => res.status(400).json(err))
    }

    const save = (req, res) => {
        if (!req.body.street.trim()) {
            return res.status(400).send('O nome da rua é um campo obrigatório')
        }
        if (!req.body.city.trim()) {
            return res.status(400).send('O nome da cidade é um campo obrigatório')
        }

        req.body.userId = req.user.id

        app.db('tb_addresses')
            .insert(req.body)
            .then(_ => res.status(204).send())
            .catch(err => res.status(400).json(err))
    }

    const remove = (req, res) => {
        app.db('tb_addresses')
            .where({ id: req.params.id, userId: req.user.id })
            .del()
            .then(rowsDeleted => {
                if (rowsDeleted > 0) {
                    res.status(204).send()
                } else {
                    const msg = `Não foi encontrada o endereço com id ${req.params.id}.`
                    res.status(400).send(msg)
                }
            })
            .catch(err => res.status(400).json(err))
    }

    const update = (req, res) => {
        app.db('tb_addresses')
            .where({ id: req.params.id, userId: req.user.id })
            .update({
                street: req.body.street,
                fone: req.body.fone,
                number: req.body.number,
                district: req.body.district,
                city: req.body.city
            })
            .then(_ => res.status(204).send())
            .catch(err => res.status(400).json(err))
    }

    return { findAll, findById, findByUserId, save, remove, update }
}