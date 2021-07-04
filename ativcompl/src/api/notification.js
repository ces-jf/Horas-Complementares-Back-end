module.exports = app => {
    const save = (req, res) => {

        req.body.userSendId = req.user.id

        app.db('tb_notifications')
            .insert(req.body)
            .then(_ => res.status(204).send())
            .catch(err => res.status(400).json(err))
    }

    const findCount = (req, res) => {
        app.db('tb_notifications')
            .where({ userRecipientId: req.user.id, read: false })
            .count('read')
            .then(notifications => res.json(notifications))
            .catch(err => res.status(400).json(err))
    }

    const findAll = (req, res) => {
        app.db('tb_notifications as n')
            .join('tb_users as u', 'n.userSendId', '=', 'u.id')
            .join('tb_activities as a', 'n.activityId', '=', 'a.id')
            .select('n.id', 'u.name as user', 'a.name as activity', 'n.read', 'n.message')
            .where({ userRecipientId: req.user.id })
            .orderBy('read')
            .then(notifications => res.json(notifications))
            .catch(err => res.status(400).json(err))
    }
    
    const updateNotificationRead = (req, res, read) => {
        app.db('tb_notifications')
            .where({ id: req.params.id, userRecipientId: req.user.id })
            .update({ read })
            .then(_ => res.status(204).send())
            .catch(err => res.status(400).json(err))
    }

    const readNotification = (req, res) => {
        app.db('tb_notifications')
            .where({ id: req.params.id, userRecipientId: req.user.id })
            .first()
            .then(notification => {
                if (!notification) {
                    const msg = `Notificação com id ${req.params.id} não encontrada.`
                    return res.status(400).send(msg)
                }

                const read = notification.read ? false : true
                updateNotificationRead(req, res, read)
            })
            .catch(err => res.status(400).json(err))
    }

    return { save, findCount, findAll, readNotification }    
}