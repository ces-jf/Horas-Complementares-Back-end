module.exports = {
    test: {
        client: 'pg',
        version: '13.3',
        connection: {
            host: 'localhost',
            user: 'postgres',
            password: 'masterkey',
            database: 'db_ativcompl',
        },
        migrations: {
            directory: 'src/migrations',
        },
    },
};
