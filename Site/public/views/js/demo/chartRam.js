let ramIndisponivel = 0;
let ramDisponivel = 0;
let corInicial = "#1cc88a";
let corCritico = "#e74a3b";

let corDisponivel = "#d19532";
let corIndisponivel = "#858796";

var exibiu_grafico = false;


if (ramIndisponivel > 80) {
  corDisponivel = corCritico;
} else {
  corDisponivel = corInicial;
}

function atualizarGrafico() {
  setTimeout(atualizarGrafico, 3000);
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
      data: [ramDisponivel,ramIndisponivel],
      backgroundColor: [corDisponivel]
    }],
    labels: ["Utilizado","Disponível"]
  };

  fetch(`/ram/ListarRam`, { method: "GET" }).then(function (response) {

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
          ramDisponivel = registro.ramAtual;
          ramIndisponivel = 100 - ramDisponivel;
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

