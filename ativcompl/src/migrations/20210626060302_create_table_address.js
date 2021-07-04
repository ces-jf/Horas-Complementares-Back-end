
exports.up = (knex) => {
    return knex.schema.createTable('tb_addresses', table => {
        table.increments('id').primary()
        table.string('street').notNull()
        table.string('fone').notNull()
        table.string('number')
        table.string('district')
        table.string('city').notNull()
        table.bigint('userId')
            .references('id')
            .inTable('tb_users').notNull()
    })
};

exports.down = (knex) => {
    return knex.schema.dropTable('tb_addresses')
};
