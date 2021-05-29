
exports.up = (knex) => {
    return knex.schema.createTable('users', (t) => {
        t.increments('id').primary();
        t.string('registration').notNull().unique();
        t.string('name').notNull();
        t.string('mail').notNull().unique();
        t.string('passwd').notNull();
    });
};

exports.down = (knex) => {
    return knex.schema.dropTable('users');
};
