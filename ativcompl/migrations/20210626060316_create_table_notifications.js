
exports.up = (knex) => {
    return knex.schema.createTable('tb_notifications', table => {
        table.increments('id').primary()
        table.bigint('userSendId')
            .references('id')
            .inTable('tb_users').notNull()
        table.bigint('userRecipientId')
            .references('id')
            .inTable('tb_users').notNull()
        table.bigint('activityId')
            .references('id')
            .inTable('tb_activities').notNull()
        table.string('message').notNull()
        table.boolean('read').notNull()
    })
};

exports.down = (knex) => {
    return knex.schema.dropTable('tb_notifications')
};
