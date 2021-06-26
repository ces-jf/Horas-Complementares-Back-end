
exports.seed = (knex) => {
  // Deletes ALL existing entries
  return knex('tb_addresses').del()
    .then(() => knex('tb_valuations').del())
    .then(() => knex('tb_notifications').del())
    .then(() => knex('tb_users_courses').del())
    .then(() => knex('tb_usertypes').del())
    .then(() => knex('tb_activities').del())
    .then(() => knex('tb_categories').del())
    .then(() => knex('tb_users').del())
    .then(() => knex('tb_courses').del())
    .then(() => knex('tb_courses').insert([
      { id: 1, name: 'Sistemas de Informação', workload: 216, limit: 72 },
      { id: 2, name: 'Engenharia de Software', workload: 144, limit: 48 }
    ])).then(() => knex('tb_usertypes').insert([
      { id: 1, name: 'Aluno', profile: 1 },
      { id: 2, name: 'Coordenador', profile: 2 },
      { id: 3, name: 'Supervisor', profile: 2 }
    ])).then(() => knex('tb_users').insert([
      {
        id: 10001,
        name: 'Marco',
        registration: 'marco',
        email: 'marco@uniacademia.edu.br',
        password: '$2a$10$aVe6woUBSqWP9LcjUoyO.egv2qHvKT0KW1y3Nn0zRQ4KNNYcdI792',
        avatar: 'https://cdn.pixabay.com/photo/2014/04/03/11/56/manager-312603_960_720.png',
        usertypeId: 2
      },
      {
        id: 10002,
        name: 'Guilherme',
        registration: 'gui',
        email: 'guilherme@uniacademia.edu.br',
        password: '$2a$10$aVe6woUBSqWP9LcjUoyO.egv2qHvKT0KW1y3Nn0zRQ4KNNYcdI792',
        avatar: 'https://cdn.pixabay.com/photo/2014/04/03/11/47/person-312160_960_720.png',
        usertypeId: 3
      }
    ])).then(() => knex('tb_categories').insert([
      { id: 1, name: 'Semana Acadêmica' },
      { id: 2, name: 'Seminário' },
      { id: 3, name: 'Palestra' },
      { id: 4, name: 'Curso de Extensão' },
    ])).then(() => knex('tb_users_courses').insert([
      {
        id: 1,
        start: "2000-02-02 00:00:00.0",
        userId: 10001,
        courseId: 1
      }, {
        id: 2,
        start: "2000-02-02 00:00:00.0",
        userId: 10001,
        courseId: 2
      }, {
        id: 3,
        start: "2000-02-02 00:00:00.0",
        userId: 10002,
        courseId: 1
      }, {
        id: 4,
        start: "2000-02-02 00:00:00.0",
        userId: 10002,
        courseId: 2
      }
    ]))



  //  {
  //   // Inserts seed entries
  //   return knex('table_name').insert([

  //   ]);
  // });
};









