// não mexa nestas 3 linhas! 
var express = require('express');
var router = express.Router();
var banco = require('../app-banco');
// não mexa nessas 3 linhas!

router.get('/ListarRam', function (req, res, next) {
  console.log(banco.conexao);

  var leituraRam = {
    ramAtual: 0,
    ramLivre: 0
  };

  banco.conectar().then(() => {
    return banco.sql.query(`select top(1) ramStatusServidor as ramAtual from tbStatusServidor where codServidor = (select codServidor from tbServidor where codServidor = 1)`);
  }).then(consulta => {
    leituraRam.ramAtual = consulta.recordset[0].ramAtual;
    leituraRam.ramLivre = 100 - Number(consulta.recordset[0].ramAtual);
    
    console.log(`Resultado da consulta: ${consulta.recordset}`);
    
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
