const { smtpConfig } = require('../../.env')
const nodemailer = require('nodemailer')
const ejs = require('ejs')
const moment = require('moment')
moment.locale('pt-br');

const email = (
    '<body style="margin: 0; padding: 0;">' +
    '<table align="center" border="0.5" cellpadding="0" cellspacing="15px" width="90%" style="border-collapse: collapse;">' +
    '<tr>' +
    '<td>' +
    '<table border="0" cellpadding="0" cellspacing="0" width="100%">' +
    '<tr>' +
    '<td width="100" valign="middle">' +
    '<img src="https://assets.website-files.com/5cf95301995e8c48a8880a69/5ec5243db66aa026f959b614_COLORIDA_1.png" width="100" height="90" style="display: block;" />' +
    '</td>' +
    '<th width="90%" valign="middle">' +
    'CENTRO UNIVERSITÁRIO ACADEMIA – UNIACADEMIA – CURSO DE <%= datas.upperCase(datas.course.name) %> – ANO: <%= datas.currentYear %>' +
    '<br />' +
    'FICHA DE SOLICITAÇÃO DE APROVEITAMENTO DE ATIVIDADES CIENTÍFICO CULTURAIS. ' +
    '</th>' +
    '</tr>' +
    '</table>' +
    '</td>' +
    '</tr>' +
    '<tr><b>1. DADOS DE IDENTIFICAÇÃO:</b></tr>' +
    '<tr>' +
    '<td width="90%"><b>NOME: </b><%= datas.user.name %></td>' +
    '</tr>' +
    '<tr>' +
    '<td width="90%"><b>ENDEREÇO: </b><%= datas.user.address %></td>' +
    '</tr>' +
    '<tr>' +
    '<td width="90%"><b>TELEFONE: </b><%= datas.user.fone %></td>' +
    '</tr>' +
    '<tr>' +
    '<td width="90%"><b>E-MAIL: </b><%= datas.user.email %></td>' +
    '</tr>' +
    '<tr>' +
    '<td><b>2. PARTICIPAÇÃO EM EVENTOS:</b></td>' +
    '</tr>' +
    '<tr>' +
    '<td>' +
    '<table border="1" cellpadding="0" cellspacing="0" width="100%">' +
    '<tr>' +
    '<th width="15%"> INSTITUIÇÃO PROMOTORA </th>' +
    '<th width="45%"> NOME DO EVENTO </th>' +
    '<th width="15%"> PERÍODO </th>' +
    '<th width="5%"> TOTAL DE HORAS </th>' +
    '<th width="20%"> CERTIFICADO </th>' +
    '</tr>' +
    '<% datas.activities.forEach(d => { %>' +
    '<tr>' +
    '<td width="15%"><%= d.institution %></td>' +
    '<td width="55%"><%= d.name %></td>' +
    '<td width="10%" style="text-align:center"><%= datas.formatDate(d.start) %> a <%= datas.formatDate(d.end) %></td>' +
    '<td width="10%" style="text-align:center"><%= d.workloadValidated %> horas</td>' +
    '<td width="10%"><%= d.certificate %></td>' +
    '</tr>' +
    '<% }) %>' +
    '</table>' +
    '</td>' +
    '</tr>' +
    '<tr>' +
    '<td>' +
    '<table border="0" cellpadding="0" cellspacing="0" width="80%">' +
    '<tr style="border-bottom:1pt solid black">' +
    '<th width="90%" style="text-align:right">TOTAL GERAL</th>' +
    '<th width="10%" style="text-align:right"><%= datas.totalWorkloadValidate %> horas</th>' +
    '</tr>' +
    '</table>' +
    '</td>' +
    '</tr>' +
    '<tr>' +
    '<td>' +
    '<table border="0" cellpadding="0" cellspacing="10px" width="100%">' +
    '<tr>' +
    '<td width="90%">' +
    'CARGA HORÁRIA MÍNIMA: <%= datas.course.workload %>' +
    '</td>' +
    '</tr>' +
    '<tr>' +
    '<td width="90%">' +
    'DESPACHO: Somos pelo aproveitamento: SIM(     ) – NÃO (     ) ' +
    '</td>' +
    '</tr>' +
    '<tr>' +
    '<td width="90%">' +
    'Enviado para a Secretaria em: ___/___/______' +
    '</td>' +
    '</tr>' +
    '<tr>' +
    '<td width="90%">' +
    'JUIZ DE FORA, <%= datas.today %> ' +
    '</td>' +
    '</tr>' +
    '<tr>' +
    '<td width="90%">' +
    'Coordenador do curso' +
    '</td>' +
    '</tr>' +
    '</table>' +
    '</td>' +
    '</tr>' +
    '</table>' +
    '</body>'
)

module.exports = app => {  

    const currentYear = moment(new Date()).format('YYYY')

    const upperCase = (str) => {
        return str.toUpperCase()
    }

    const formatDate = (date) => {
        return moment(new Date(date)).format('DD/MM/YYYY')
    }

    const sendEmail = async (req, res) => {

       const datas = {
            user: req.body.user,
            course: req.body.course,
            activities: req.body.activities,
            totalWorkloadValidate: req.body.totalWorkloadValidate,
            currentYear: currentYear,
            today: moment(new Date()).format('LL'),
            formatDate,
            upperCase
        }

        const html = ejs.render(email, { datas: datas })

        const transport = nodemailer.createTransport({
            host: smtpConfig.host,
            port: smtpConfig.port,
            auth: {
                user: smtpConfig.user,
                pass: smtpConfig.pass
            }
        })

        let message = await transport.sendMail({
            from: `${datas.user.name} <${smtpConfig.user}>`,
            to: `${datas.user.name} <${datas.user.email}>, ${datas.user.name} <${smtpConfig.user}>`,
            replyTo: `${datas.user.name} <${smtpConfig.user}>`,
            subject: 'Atividades Complementares',
            html
        }).then(info => {
            res.send(info)
        }).catch(error => {
            res.send(error)
        })
    }

    return { sendEmail }
}