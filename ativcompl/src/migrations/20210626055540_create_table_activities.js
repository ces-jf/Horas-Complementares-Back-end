
exports.up = (knex) => {
    return knex.schema.createTable('tb_activities', table => {
        table.increments('id').primary()
        table.string('name').notNull()
        table.string('institution').notNull()
        table.datetime('start').notNull()
        table.datetime('end')
        table.double('workload').notNull()
        table.double('workloadValidated')
        table.boolean('completed')
        table.string('certificate')
        table.bigint('userId')
            .references('id')
            .inTable('tb_users').notNull()
        table.bigint('categoryId')
            .references('id')
            .inTable('tb_categories').notNull()
        table.bigint('courseId')
            .references('id')
            .inTable('tb_courses').notNull()
    })
};

exports.down = (knex) => {
    return knex.schema.dropTable('tb_activities')
};
