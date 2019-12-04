let minutosIndisponiveis = 0;
let minutosDisponiveis = 0;

let corInicial = "#1cc88a";
let corCritico = "#e74a3b";

let corDisponivel = "#1cc88a";
let corIndisponivel = "#858796";

let exibiuGraficoDisponibilidade = false;

var configuracoesDisponibilidade;

var porcentagemIndisponivel = 0;
var porcentagemDisponivel = 0;

var dadosDisponibilidade;

function atualizarGraficos() {
  setTimeout(atualizarGraficos, 10000);
  stop(10000);
  setInterval(obterDadosGraficos(), 3000);
}

Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#858796';


function configurarGraficoDisponibilidade() {
  configuracoesDisponibilidade = {
    maintainAspectRatio: false,
    responsive: true,
    animation: exibiuGraficoDisponibilidade ? false : { duration: 1500 },
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
      display: true,
      position: "bottom"
    },
    cutoutPercentage: 70,
  };

  return configuracoesDisponibilidade;
}

function obterDadosGraficos() {

  dadosDisponibilidade = {

    datasets: [{
      data: [ minutosDisponiveis, minutosIndisponiveis],
      backgroundColor: [corDisponivel, corIndisponivel]
    }],
    labels: [`Disponivel (${ porcentagemIndisponivel }%)`, `Indisponivel (${porcentagemDisponivel}%)`]
  };

  fetch(`/servidor/ListarDisponibilidade`, { method: "GET" }).then(function (response) {

    if (response.ok) {
      response.json().then(function (resposta) {

        console.log(`Dados recebidos: ${JSON.stringify(resposta)}`);

        resposta.reverse();

        for (i = 0; i < resposta.length; i++) {
          var registro = resposta[i];

          minutosIndisponiveis = registro.indisMes;
          minutosDisponiveis = registro.dispMes;

          porcentagemDisponivel = (minutosIndisponiveis * 100) / (minutosIndisponiveis + minutosDisponiveis);
          porcentagemIndisponivel = 100 - porcentagemDisponivel;

          porcentagemDisponivel = parseFloat(porcentagemDisponivel).toFixed(2);
          porcentagemIndisponivel = parseFloat(porcentagemIndisponivel).toFixed(2);



          if (minutosDisponiveis <= 20) {
            corDisponivel = corCritico;
          } else {
            corDisponivel = corInicial;
          }

          document.getElementById('lbNomeServidor').innerHTML = registro.linkServidor;
          document.getElementById('lbStatusServidor').innerHTML = registro.statusServidor;

        }
        console.log(JSON.stringify(dadosDisponibilidade));


        plotarGraficoRam(dadosDisponibilidade);
      });
    } else {
      console.error('Nenhum dado encontrado ou erro na API');
    }
  })
    .catch(function (error) {
      console.error(`Erro na obtenção dos dados p/ gráfico: ${error.message}`);
    });
}



function plotarGraficoRam(dadosDisponibilidade) {
  console.log('iniciando plotagem do gráfico de RAM');

  var ctx = document.getElementById("graficoDisponibilidade");
  if (window.myChartRam) {
    window.myChartRam.data = dadosDisponibilidade;
    window.myChartRam.options = configurarGraficoDisponibilidade();
    window.myChartRam.update();
    exibiuGraficoDisponibilidade = true;
  } else {
    window.myChartRam = new Chart(ctx, {
      type: 'doughnut',
      data: dadosDisponibilidade,
      options: configurarGraficoDisponibilidade()
    });
  }
}

