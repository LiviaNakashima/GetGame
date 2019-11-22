let ramIndisponivel = 0;
let ramDisponivel = 0;
let corInicial = "#1cc88a";
let corCritico = "#e74a3b";

let corDisponivel = "#1cc88a";
let corIndisponivel = "#858796";

var exibiu_grafico = false;


function atualizarGraficoCpu() {
  setTimeout(atualizarGraficoCpu, 3000);
  stop(3000);
  setInterval(obterDadosGrafico(), 3000);
}

Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#858796';


function configurarGrafico() {
  var configuracoes = {
    maintainAspectRatio: false,
    animation: exibiu_grafico ? false : {duration: 1500},
    tooltips: {
      backgroundColor: "rgb(255,255,255)",
      bodyFontColor: "#858796",
      borderColor: '#dddfeb',
      borderWidth: 1,
      xPadding: 15,
      yPadding: 15,
      displayColors: false,
      caretPadding: 10,
    },
    legend: {
      display: false
    },
    cutoutPercentage: 70,
  };

  exibiu_grafico = true;

  return configuracoes;
}

function obterDadosGrafico() {

  var dados = {

    datasets: [{
      data: [ramIndisponivel,ramDisponivel],
      backgroundColor: [corIndisponivel,corDisponivel]
    }],
    labels: ["Utilizado","Disponível"]
  };

  fetch(`/hardware/ListarDados`, { method: "GET" }).then(function (response) {

    if (response.ok) {
      response.json().then(function (resposta) {

        console.log(`Dados recebidos: ${JSON.stringify(resposta)}`);

        resposta.reverse();

        for (i = 0; i < resposta.length; i++) {
          var registro = resposta[i];

          // aqui, após 'registro.' use os nomes 
          // dos atributos que vem no JSON 
          // que gerou na consulta ao banco de dados

          //dados.datasets[0].data.push(registro.ramAtual);
          ramIndisponivel = parseFloat(registro.ramAtual).toFixed(2);
          ramDisponivel = parseFloat(100 - ramIndisponivel).toFixed(2);
          if (ramDisponivel <= 20) {
            corDisponivel = corCritico;
          } else {
            corDisponivel = corInicial;
          }
        }
        console.log(JSON.stringify(dados));


        plotarGrafico(dados);
      });
    } else {
      console.error('Nenhum dado encontrado ou erro na API');
    }
  })
    .catch(function (error) {
      console.error(`Erro na obtenção dos dados p/ gráfico: ${error.message}`);
    });
}

function plotarGrafico(dados) {
  console.log('iniciando plotagem do gráfico...');

  var ctx = document.getElementById("myRamChart");
  window.myRamChart = new Chart(ctx, {
    type: 'doughnut',
    data: dados,
    options: configurarGrafico()
  });

}

