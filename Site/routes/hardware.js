// não mexa nestas 3 linhas! 
var express = require('express');
var router = express.Router();
var banco = require('../app-banco');
// não mexa nessas 3 linhas!


// MEMORIA RAM
router.get('/ListarDados', function (req, res, next) {
  console.log(banco.conexao);

  var leituraDados = {
    ramAtual: 0,
    
    cpuAtual: 0,

    discoAtual: 0,

    dataHora:0
  };

  banco.conectar().then(() => {
    return banco.sql.query(`select top(1) ramStatusServidor as ramAtual, 
    cpuStatusServidor as cpuAtual, 
    discoStatusServidor as discoAtual,
    dataHoraStatusServidor as dataHora from tbStatusServidor
    where codServidor = (select codServidor from tbServidor where codServidor = 1) 
    ORDER BY codStatusServidor desc;`);
  }).then(consulta => {
    leituraDados.ramAtual = consulta.recordset[0].ramAtual;
    leituraDados.cpuAtual = consulta.recordset[0].cpuAtual;    
    leituraDados.discoAtual = consulta.recordset[0].discoAtual;  
    leituraDados.dataHora = consulta.recordset[0].dataHora;


    console.log(`Resultado da consulta: ${consulta}`);
    
    if(consulta.recordset.length==0){
        res.status(404).send("Nenhum dado de memoria ram encontrado");
    } else {
        res.send(consulta.recordset);
    }

  }).catch(err => {

    var erro = `Erro na leitura dos últimos registros: ${err}`;
    console.error(erro);
    res.status(500).send(erro);

  }).finally(() => {
    banco.sql.close();
  });

}); 





// não mexa nesta linha!
module.exports = router;
