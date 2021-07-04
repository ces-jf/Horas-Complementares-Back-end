module.exports = app => {
    const save = (req, res) => {
        app.db('tb_valuations')
            .insert(req.body)
            .then(_ => res.status(204).send())
            .catch(err => res.status(400).json(err))
    }

    return { save }
}