const express = require('express')
const app = express()
const db = require('./src/config/db')
const consign = require('consign')

consign({ cwd: 'src', verbose: false })
    .include('./config/passport.js')
    .then('./config/middlewares.js')
    .then('./api')
    .then('./config/routes.js')
    .into(app)

app.db = db

app.listen(3000, () => {
    console.log('Backend Atividades Complementares em execução...')
})