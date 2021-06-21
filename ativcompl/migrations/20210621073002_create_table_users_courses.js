
exports.up = function (knex, Promise) {
    return knex.schema.createTable('tb_users_courses', table => {
        table.increments('id').primary()
        table.datetime('start')
        table.bigint('userId')
            .references('id')
            .inTable('tb_users').notNull()
        table.bigint('courseId')
            .references('id')
            .inTable('tb_courses').notNull()
        table.bigint('usertypeId')
            .references('id')
            .inTable('tb_usertypes').notNull()
    })
};

exports.down = function (knex, Promise) {
    return knex.schema.dropTable('tb_users_courses')
};
