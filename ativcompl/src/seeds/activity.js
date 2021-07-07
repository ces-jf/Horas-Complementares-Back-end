
exports.seed = (knex) => {
  // Deletes ALL existing entries
  return knex('tb_notifications').del()
    .then(() => knex('tb_addresses').del())
    .then(() => knex('tb_valuations').del())
    .then(() => knex('tb_users_courses').del())
    .then(() => knex('tb_activities').del())
    .then(() => knex('tb_categories').del())
    .then(() => knex('tb_users').del())
    .then(() => knex('tb_usertypes').del())
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
        name: 'Conrado Silva',
        registration: 'co123',
        email: 'conrrado@uniacademia.edu.br',
        password: '$2a$10$aVe6woUBSqWP9LcjUoyO.egv2qHvKT0KW1y3Nn0zRQ4KNNYcdI792',
        avatar: 'https://cdn.pixabay.com/photo/2014/04/03/11/56/manager-312603_960_720.png',
        usertypeId: 2
      },
      {
        id: 10002,
        name: 'Sullivan Oliveira',
        registration: 'su123',
        email: 'sulivan@uniacademia.edu.br',
        password: '$2a$10$aVe6woUBSqWP9LcjUoyO.egv2qHvKT0KW1y3Nn0zRQ4KNNYcdI792',
        avatar: 'https://cdn.pixabay.com/photo/2014/04/03/11/47/person-312160_960_720.png',
        usertypeId: 3
      },
      {
        id: 10003,
        name: 'Paulo Toledo',
        registration: 'paulo123',
        email: 'ptoledo.bsices@gmail.com',
        password: '$2a$10$aVe6woUBSqWP9LcjUoyO.egv2qHvKT0KW1y3Nn0zRQ4KNNYcdI792',
        avatar: 'https://cdn.pixabay.com/photo/2014/04/03/11/47/person-312160_960_720.png',
        usertypeId: 1
      }
    ])).then(() => knex('tb_categories').insert([
      { id: 1, name: 'Exercício de monitoria' },
      { id: 2, name: 'Iniciação Científica ' },
      { id: 3, name: 'Participação em projetos de extensão promovidos pelo UniAcademia' },
      { id: 4, name: 'Participação em grupos de estudo no UniAcademia ou em outra IES reconhecida pelo Ministério da Educação' },
      { id: 5, name: 'Minicursos' },
      { id: 6, name: 'Cursos de extensão' },
      { id: 7, name: 'Seminários' },
      { id: 8, name: 'Congressos' },
      { id: 9, name: 'Simpósios' },
      { id: 10, name: 'Workshops' },
      { id: 11, name: 'Mesas-redondas' },
      { id: 12, name: 'Oficinas e outras ' },
      { id: 13, name: 'Conselhos e órgãos de classe' },
      { id: 14, name: 'Sociedades' },
      { id: 15, name: 'Organizações e similares' },
      { id: 16, name: 'Representação estudantil de turma e em órgãos colegiados do UniAcademia' },
      { id: 17, name: 'Presença, como ouvinte, em defesa oral de TCC (Trabalho de Conclusão de Curso), dissertação de Mestrado ou de tese de Doutorado em área afim' },
      { id: 18, name: 'Produção individual ou coletiva de livros e artigos didáticos ou científicos' },
      { id: 19, name: 'Capítulo de livros' },
      { id: 20, name: 'Produção de softwares' },
      { id: 21, name: 'Vídeos e filmes publicados e com respectiva comprovação' },
      { id: 22, name: 'Realização de estágios extracurriculares' },
      { id: 23, name: 'Participação em atividades de atendimento externo ao público como atividades comunitárias' },
      { id: 24, name: 'Programas sociais; desde que aprovados pela coordenação do curso e acompanhados dos responsáveis pela prática real' },
      { id: 25, name: 'disciplinas que não integrem a matriz curricular do curso em que o estudante está matriculado' },
      { id: 26, name: 'Semana acadêmica' }
    ])).then(() => knex('tb_users_courses').insert([
      {
        id: 10001,
        start: "2000-02-02 00:00:00.0",
        userId: 10001,
        courseId: 1
      }, {
        id: 10002,
        start: "2000-02-02 00:00:00.0",
        userId: 10001,
        courseId: 2
      }, {
        id: 10003,
        start: "2000-02-02 00:00:00.0",
        userId: 10002,
        courseId: 1
      }, {
        id: 10004,
        start: "2000-02-02 00:00:00.0",
        userId: 10002,
        courseId: 2
      }
    ])).then(() => knex('tb_activities').insert([
      {
        id: 10001,
        name: "XXVI Semana de Informática",
        institution: "Centro de Ensino Superior de Juiz de Fora",
        start: "2016-10-03 00:00:00.0",
        end: "2016-10-07 00:00:00.0",
        workload: 17.5,
        workloadValidated: 43.5,
        completed: true,
        certificate: "https://drive.google.com/file/d/14taPw2xbpjrj9EQfp5dBqVIJelWYoCnW/view?usp=sharing",
        userId: 10003,
        categoryId: 26,
        courseId: 1
      },
      {
        id: 10002,
        name: "XXIV Semana de Informática",
        institution: "Centro de Ensino Superior de Juiz de Fora",
        start: "20014-10-06 00:00:00.0",
        end: "2014-10-10 00:00:00.0",
        workload: 15,
        workloadValidated: 41,
        completed: true,
        certificate: "https://drive.google.com/file/d/1kSoDYeunXAuJcfe9_Fy26LvUMhUro9Jw/view?usp=sharing",
        userId: 10003,
        categoryId: 26,
        courseId: 1
      },
      {
        id: 10003,
        name: "II Semana das Engenharias e Sistemas de Informação do CES/JF",
        institution: "Centro de Ensino Superior de Juiz de Fora",
        start: "2017-10-16 00:00:00.0",
        end: "2017-10-20 00:00:00.0",
        workload: 18.4,
        workloadValidated: 44.4,
        completed: true,
        certificate: "https://drive.google.com/file/d/16t2OWdlZhwil9KufnBDYVSaE0pEChjN3/view?usp=sharing",
        userId: 10003,
        categoryId: 26,
        courseId: 1
      },
      {
        id: 10004,
        name: "JAVASCRIPT: PROGRAMANDO NA LINGUAGEM DA WEB",
        institution: "Alura",
        start: "2018-11-06 00:00:00.0",
        end: "2018-12-02 00:00:00.0",
        workload: 20,
        workloadValidated: 46,
        completed: true,
        certificate: "https://cursos.alura.com.br/certificate/9a4aca1d-cd5e-4eb8-bc99-a9270e02a919",
        userId: 10003,
        categoryId: 6,
        courseId: 1
      },
      {
        id: 10005,
        name: "API REST em Node.JS aplicando testes (TDD) desde o princípio",
        institution: "Udemy",
        start: "2000-02-02 00:00:00.0",
        end: "2000-02-02 00:00:00.0",
        workload: 15.5,
        workloadValidated: 41.5,
        completed: true,
        certificate: "https://udemy-certificate.s3.amazonaws.com/image/UC-27e8929b-a26f-4464-af05-f714237d15e5.jpg",
        userId: 10003,
        categoryId: 6,
        courseId: 1
      }
    ]))

    //  {
  //   // Inserts seed entries
  //   return knex('table_name').insert([

  //   ]);
  // });
};









