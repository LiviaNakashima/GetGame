var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');

var indexRouter = require('./routes/index');
var usuariosRouter = require('./routes/usuarios');
var auditoriaRouter = require('./routes/auditoria');
var servidorRouter = require('./routes/servidor');
var permissaoRouter = require('./routes/permissao');
var hardwareRouter = require('./routes/hardware');

var app = express();

app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

app.use('/', indexRouter);
app.use('/usuarios', usuariosRouter);
app.use('/auditoria', auditoriaRouter);
app.use('/servidor', servidorRouter);
app.use('/permissao', permissaoRouter);
app.use('/hardware',hardwareRouter);

module.exports = app;
