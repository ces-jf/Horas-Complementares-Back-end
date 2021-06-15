
exports.up = function(knex, Promise) {
    return knex.schema.createTable('tb_usertypes', table => {
        table.increments('id').primary()
        table.string('name').notNull()
        table.integer('profile').notNull()
    })
};

exports.down = function(knex, Promise) {
    return knex.schema.dropTable('tb_usertypes')
};
