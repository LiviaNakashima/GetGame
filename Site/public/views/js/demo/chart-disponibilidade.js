let disponibilidade = 0;
let indisponibilidade = 0;
let corInicial = "#1cc88a";
let corCritico = "#e74a3b";

let corDisponivel = "#1cc88a";
let corIndisponivel = "#858796";

var exibiuGrafico = false;


function atualizarGraficoDisponibilidade() {
  setTimeout(atualizarGraficoDisponibilidade, 3000);
  stop(3000);
  setInterval(obterDadosGrafico(), 3000);
}

Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#858796';


function configurarGraficoDisponibilidade() {
  var configuracoesDisponibilidade = {
    maintainAspectRatio: false,
    animation: exibiuGraficoDisponibilidade ? false : {duration: 1500},
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

  exibiuGraficoDisponibilidade = true;

  return configuracoesDisponibilidade;
}

function obterDadosGrafico() {

  var dadosDisponibilidade = {

    datasets: [{
      data: [indisponivel,disponivel],
      backgroundColor: [corIndisponivel,corDisponivel]
    }],
    labels: ["No ar","Fora do ar"]
  };

  fetch(`/servidor/ListarDisponibilidade`, { method: "GET" }).then(function (response) {

    if (response.ok) {
      response.json().then(function (resposta) {

        console.log(`Dados recebidos: ${JSON.stringify(resposta)}`);

        var registro = resposta[0]+resposta[1];
        registro - 100
        resposta[1] - x
        disponivel = (resposta[1]*100)/registro;
        indisponivel = 100 - disponivel;

        if (disponivel <= 20) {
          corDisponivel = corCritico;
        } else {
          corDisponivel = corInicial;
        }
        
        console.log(JSON.stringify(disponivel));

        plotarGraficoDisponivel(disponivel);
      });
    } else {
      console.error('Nenhum dado encontrado ou erro na API');
    }
  })
    .catch(function (error) {
      console.error(`Erro na obtenção dos dadosCpu p/ gráfico: ${error.message}`);
    });
}

function plotarGraficoDisponivel(dadosDisponibilidade) {
  console.log('iniciando plotagem do gráfico...');

  var ctx = document.getElementById("myCpuChart");
  window.myCpuChart = new Chart(ctx, {
    type: 'doughnut',
    data: dadosDisponibilidade,
    options: configurarGraficoDisponibilidade()
  });

}

