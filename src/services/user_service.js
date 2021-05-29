module.exports = (app) => {
    const findAll = (filter = []) => {
        return app.db('users').where(filter).select();
    };

    const save = async (user) => {
        if(!user.name) return { error: 'O nome do usuário é um atributo obrigatório'};
        if(!user.registration) return { error: 'A matrícula do usuário é um atributo obrigatório'};
        if(!user.email) return { error: 'O email do usuário é um atributo obrigatório'};
        if(!user.passwd) return { error: 'A senha do usuário é um atributo obrigatório'};

        const userReg = await findAll({ registration: user.registration });
        if(userReg && userReg.length > 0) return { error: 'Existe um usuário com essa matrícula'}

        const userEmail = await findAll({ email: user.email });
        if(userEmail && userEmail.length > 0) return { error: 'Existe um usuário com esse email'}

        return app.db('users').insert(user, '*');
    };

    return { findAll, save }
};
