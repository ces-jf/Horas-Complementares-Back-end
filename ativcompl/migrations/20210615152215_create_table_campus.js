exports.up = function(knex, Promise) {
    return knex.schema.createTable('tb_campus', table => {
        table.increments('id').primary()
        table.string('name').notNull()
    })
};

exports.down = function(knex, Promise) {
    return knex.schema.dropTable('tb_campus')
};
