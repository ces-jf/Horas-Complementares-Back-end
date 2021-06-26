
exports.up = (knex) => {
    return knex.schema.createTable('tb_users', table => {
        table.increments('id').primary()
        table.string('name').notNull()
        table.string('registration').notNull().unique()
        table.string('email').notNull().unique()
        table.string('password').notNull()
        table.string('avatar')
        table.bigint('usertypeId')
            .references('id')
            .inTable('tb_usertypes').notNull()
    })
};

exports.down = (knex) => {
    return knex.schema.dropTable('tb_users')
};
