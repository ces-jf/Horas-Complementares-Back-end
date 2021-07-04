exports.up = (knex) => {
    return knex.schema.createTable('tb_categories', table => {
        table.increments('id').primary()
        table.string('name').notNull()
    })
};

exports.down = (knex) => {
    return knex.schema.dropTable('tb_categories')
};
