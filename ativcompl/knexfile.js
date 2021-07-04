module.exports = {
    client: 'postgresql',
    connection: {
      database: 'dev_ativcompl',
      user:     'postgres',
      password: 'masterkey'
    },
    pool: {
      min: 2,
      max: 10
    },
    migrations: {
      tableName: 'knex_migrations',
      directory: 'src/migrations'
    },
    seeds: {
      directory: 'src/seeds'
  }
};
