let cpuIndisponivel = 0;
let cpuDisponivel = 0;
let corInicialCpu = "#1cc88a";
let corCriticoCpu = "#e74a3b";

let corDisponivelCpu = "#1cc88a";
let corIndisponivelCpu = "#858796";

var exibiuGraficoCpu = false;


function atualizarGraficoCpu() {
  setTimeout(atualizarGraficoCpu, 3000);
  stop(3000);
  setInterval(obterDadosGrafico(), 3000);
}

Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#858796';


function configurarGraficoCpu() {
  var configuracoesCpu = {
    maintainAspectRatio: false,
    animation: exibiuGraficoCpu ? false : {duration: 1500},
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

  exibiuGraficoCpu = true;

  return configuracoesCpu;
}

function obterDadosGrafico() {

  var dadosCpu = {

    datasets: [{
      data: [cpuIndisponivel,cpuDisponivel],
      backgroundColor: [corIndisponivelCpu,corDisponivelCpu]
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
          // que gerou na consulta ao banco de dadosCpu

          //dadosCpu.datasets[0].data.push(registro.ramAtual);
          cpuIndisponivel = parseFloat(registro.cpuAtual).toFixed(2);
          cpuDisponivel = parseFloat(100 - cpuIndisponivel).toFixed(2);
          if (cpuDisponivel <= 20) {
            corDisponivelCpu = corCriticoCpu;
          } else {
            corDisponivelCpu = corInicialCpu;
          }
        }
        console.log(JSON.stringify(dadosCpu));


        plotarGraficoCpu(dadosCpu);
      });
    } else {
      console.error('Nenhum dado encontrado ou erro na API');
    }
  })
    .catch(function (error) {
      console.error(`Erro na obtenção dos dadosCpu p/ gráfico: ${error.message}`);
    });
}

function plotarGraficoCpu(dadosCpu) {
  console.log('iniciando plotagem do gráfico...');

  var ctx = document.getElementById("myCpuChart");
  window.myCpuChart = new Chart(ctx, {
    type: 'doughnut',
    data: dadosCpu,
    options: configurarGraficoCpu()
  });

}

