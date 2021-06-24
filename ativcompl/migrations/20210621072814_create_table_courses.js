exports.up = function(knex, Promise) {
    return knex.schema.createTable('tb_courses', table => {
        table.increments('id').primary()
        table.string('name').notNull()
        table.double('workload')
        table.double('limit')
        table.boolean('completed')
    })
};

exports.down = function(knex, Promise) {
    return knex.schema.dropTable('tb_courses')
};
