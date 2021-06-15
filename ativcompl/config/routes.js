module.exports = app => {
    app.post('/signup', app.api.user.save)
    app.post('/signin', app.api.auth.signin)

    // Atividades
    app.route('/activities')
        .all(app.config.passport.authenticate())
        .get(app.api.activity.findAll)
        .post(app.api.activity.save)

    app.route('/activities/:id')
        .all(app.config.passport.authenticate())
        .get(app.api.activity.findById)
        .delete(app.api.activity.remove)

    app.route('/activities/:id/toggle')
        .all(app.config.passport.authenticate())
        .put(app.api.activity.toggleActivity)

    app.route('/activities/:id/date')
        .all(app.config.passport.authenticate())
        .put(app.api.activity.findAllDeadline)

    //Categorias
    app.route('/categories')
        .all(app.config.passport.authenticate())
        .get(app.api.category.findAll)
        .post(app.api.category.save)

    app.route('/categories/:id')
        .all(app.config.passport.authenticate())
        .get(app.api.category.findById)
        .delete(app.api.category.remove)
        .put(app.api.category.update)

    //Endereços
    app.route('/addresses')
        .all(app.config.passport.authenticate())
        .get(app.api.address.findAll)
        .post(app.api.address.save)

    app.route('/addresses/:id')
        .all(app.config.passport.authenticate())
        .get(app.api.address.findById)
        .delete(app.api.address.remove)
        .put(app.api.address.update)

    app.route('/address')
        .all(app.config.passport.authenticate())
        .get(app.api.address.findByUserId)

    // Campus
    app.route('/campus')
        .all(app.config.passport.authenticate())
        .get(app.api.campus.findAll)
        .post(app.api.campus.save)

    app.route('/campus/:id')
        .all(app.config.passport.authenticate())
        .get(app.api.campus.findById)
        .delete(app.api.campus.remove)
        .put(app.api.campus.update)

    //Cursos
    app.route('/courses')
        .all(app.config.passport.authenticate())
        .get(app.api.course.findAll)
        .post(app.api.course.save)

    app.route('/courses/:id')
        .all(app.config.passport.authenticate())
        .get(app.api.course.findById)
        .delete(app.api.course.remove)
        .put(app.api.course.update)

    //Usuário X Curso
    app.route('/users_courses')
        .all(app.config.passport.authenticate())
        .get(app.api.user_course.findAll)
        .post(app.api.user_course.save)

    app.route('/users_courses/:id')
        .all(app.config.passport.authenticate())
        .get(app.api.user_course.findById)
        .delete(app.api.user_course.remove)
        .put(app.api.user_course.updateCourse)

    app.route('/users_courses/:id/usertype')
        .all(app.config.passport.authenticate())
        .put(app.api.user_course.updateUserType)

    //Tipo de Usuário
    app.route('/usertypes')
        .all(app.config.passport.authenticate())
        .get(app.api.usertype.findAll)
        .post(app.api.usertype.save)

    app.route('/usertypes/:id')
        .all(app.config.passport.authenticate())
        .get(app.api.usertype.findById)
        .delete(app.api.usertype.remove)
        .put(app.api.usertype.update)

}