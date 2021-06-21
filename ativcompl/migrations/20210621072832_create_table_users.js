
exports.up = function(knex, Promise) {
    return knex.schema.createTable('tb_users', table => {
        table.increments('id').primary()
        table.string('name').notNull()
        table.string('registration').notNull().unique()
        table.string('email').notNull().unique()
        table.string('password').notNull()
    })
};

exports.down = function(knex, Promise) {
    return knex.schema.dropTable('tb_users')
};
