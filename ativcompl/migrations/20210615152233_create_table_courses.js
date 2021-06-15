exports.up = function(knex, Promise) {
    return knex.schema.createTable('tb_courses', table => {
        table.increments('id').primary()
        table.string('name').notNull()
        table.double('workload')
        table.bigint('campusId')
            .references('id')
            .inTable('tb_campus').notNull()
    })
};

exports.down = function(knex, Promise) {
    return knex.schema.dropTable('tb_courses')
};