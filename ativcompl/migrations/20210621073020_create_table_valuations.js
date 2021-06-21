
exports.up = function(knex, Promise) {
    return knex.schema.createTable('tb_valuations', table => {
        table.increments('id').primary()
        table.integer('valuation').notNull()
        table.string('justification')
        table.bigint('activityId')
            .references('id')
            .inTable('tb_activities').notNull()
    })
};

exports.down = function(knex, Promise) {
    return knex.schema.dropTable('tb_valuations')
};
