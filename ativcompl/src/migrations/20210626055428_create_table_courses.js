exports.up = (knex) => {
    return knex.schema.createTable('tb_courses', table => {
        table.increments('id').primary()
        table.string('name').notNull()
        table.double('workload')
        table.double('limit')
    })
};

exports.down = (knex) => {
    return knex.schema.dropTable('tb_courses')
};
