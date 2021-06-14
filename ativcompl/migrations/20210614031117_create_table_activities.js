
exports.up = function(knex, Promise) {
    return knex.schema.createTable('tb_activities', table => {
        table.increments('id').primary()
        table.string('name').notNull()
        table.datetime('start')
        table.double('workload').notNull()
        table.double('hoursCompleted')
        table.boolean('closed')
        table.string('certificate')
        table.bigint('userId')
            .references('id')
            .inTable('tb_users').notNull()
        table.bigint('categoryId')
            .references('id')
            .inTable('tb_categories').notNull()
    })
};

exports.down = function(knex, Promise) {
    return knex.schema.dropTable('tb_activities')
};
