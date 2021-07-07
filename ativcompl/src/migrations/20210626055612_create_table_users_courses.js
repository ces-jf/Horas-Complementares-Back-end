
exports.up = (knex) => {
    return knex.schema.createTable('tb_users_courses', table => {
        table.increments('id').primary()
        table.datetime('start')
        table.boolean('completed')
        table.bigint('userId')
            .references('id')
            .inTable('tb_users').notNull()
        table.bigint('courseId')
            .references('id')
            .inTable('tb_courses').notNull()
    })
};

exports.down = (knex) => {
    return knex.schema.dropTable('tb_users_courses')
};
