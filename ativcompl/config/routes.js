module.exports = app => {
    app.post('/signup', app.api.user.save)
    app.post('/signin', app.api.auth.signin)

    // Atividades
    app.route('/activities/teste')
        .get(app.api.activity.findAllTeste)
        .post(app.api.activity.saveTeste)

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

    app.route('/activities/:id/certificate')
        .all(app.config.passport.authenticate())
        .put(app.api.activity.certificateActivity)


    app.route('/activitiesworkload')
        .all(app.config.passport.authenticate())
        .get(app.api.activity.findWorkloadCompleted)

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

    app.route('/courses/campus/:id')
        .all(app.config.passport.authenticate())
        .get(app.api.course.findByCampus)

    //Usuário X Curso
    app.route('/users_courses')
        .all(app.config.passport.authenticate())
        .get(app.api.user_course.findUserCourse)
        .post(app.api.user_course.save)

    app.route('/users_courses/all')
        .all(app.config.passport.authenticate())
        .get(app.api.user_course.findAll)

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

    //Perfil de usuário
    app.route('/profile')
        .all(app.config.passport.authenticate())
        .get(app.api.profile.findAll)

    app.route('/profile/:id')
        .all(app.config.passport.authenticate())
        .get(app.api.profile.find)
        .put(app.api.profile.update)
        .put(app.api.profile.updatePassword)


    //Rotas do Administrador do Sistema
    //Categorias
    app.route('/categories/adm')
        .get(app.api.category.findAll)
        .post(app.api.category.save)

    //Cursos
    app.route('/courses/adm')
        .get(app.api.course.findAll)
        .post(app.api.course.save)

    //Usuário X Curso
    app.route('/users_courses/adm')
        .post(app.api.user_course.saveIni)


    //Tipo de Usuário
    app.route('/usertypes/adm')
        .get(app.api.usertype.findAll)
        .post(app.api.usertype.save)


}