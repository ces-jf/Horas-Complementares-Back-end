const { authSecret } = require('../../.env')
const jwt = require('jwt-simple')
const bcrypt = require('bcrypt-nodejs')

module.exports = app => {
    const signin = async (req, res) => {
        if (!req.body.registration || !req.body.password) {
            return res.status(400).send('Dados incompletos')
        }

        const user = await app.db('tb_users')
            .where({ registration: req.body.registration })
            .first()

        const userCourse = await app.db('tb_users_courses')
            .select('id')
            .where({ userId: user.id })
            .first()

        if (user) {
            bcrypt.compare(req.body.password, user.password, (err, isMatch) => {
                if (err || !isMatch) {
                    return res.status(401).send('Senha inválida!')
                }

                const payload = { id: user.id }

                res.json({
                    name: user.name,
                    registration: user.registration,
                    email: user.email,
                    usertypeId: user.usertypeId,
                    token: jwt.encode(payload, authSecret),
                    userCourse: userCourse
                })
            })
        } else {
            res.status(400).send('Usuário não cadastrado!')
        }
    }

    return { signin }
}