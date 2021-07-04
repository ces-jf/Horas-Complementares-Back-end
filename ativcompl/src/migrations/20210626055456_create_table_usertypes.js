
exports.up = (knex) => {
    return knex.schema.createTable('tb_usertypes', table => {
        table.increments('id').primary()
        table.string('name').notNull()
        table.integer('profile').notNull()
    })
};

exports.down = (knex) => {
    return knex.schema.dropTable('tb_usertypes')
};
