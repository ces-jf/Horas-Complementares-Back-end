module.exports = app => {
    app.post('/signup', app.api.user.save)
    app.post('/signin', app.api.auth.signin)

    app.route('/activities')
        .all(app.config.passport.authenticate())
        .get(app.api.activity.getActivities)
        .post(app.api.activity.save)

    app.route('/activities/:id')
        .all(app.config.passport.authenticate())
        .delete(app.api.activity.remove)

    app.route('/activities/:id/toggle')
        .all(app.config.passport.authenticate())
        .put(app.api.activity.toggleActivity)
}