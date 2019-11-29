let ramIndisponivel = 0;
let ramDisponivel = 0;

let cpuIndisponivel = 0;
let cpuDisponivel = 0;

let discoIndisponivel = 0;
let discoDisponivel = 0;

let corInicial = "#1cc88a";
let corCritico = "#e74a3b";

let corDisponivelRam = "#1cc88a";
let corIndisponivelRam = "#858796";

let corDisponivelCpu = "#1cc88a";
let corIndisponivelCpu = "#858796";

let corDisponivelDisco = "#1cc88a";
let corIndisponivelDisco = "#858796";

var exibiuGraficoRam = false;
var exibiuGraficoCpu = false;
var exibiuGraficoDisco = false;

var configuracoesDisco;
var configuracoesRam;
var configuracoesCpu;

var dadosDisco;
var dadosRam;
var dadosCpu;


function atualizarGraficos() {
  setTimeout(atualizarGraficos, 10000);
  stop(10000);
  setInterval(obterDadosGraficos(), 3000);
}

Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#858796';

// RAM CHART
function configurarGraficoRam() {
  configuracoesRam = {
    maintainAspectRatio: false,
    responsive: true,
    animation: exibiuGraficoRam ? false : { duration: 1500 },
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

  exibiuGraficoRam = true;

  return configuracoesRam;
}

// CPU CHART
function configurarGraficoCpu() {
  configuracoesCpu = {
    maintainAspectRatio: false,
    responsive: true,
    animation: exibiuGraficoCpu ? false : { duration: 1000 },
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

  exibiuGraficoCpu = true;

  return configuracoesCpu;
}

// DISCO CHART
function configurarGraficoDisco() {
  configuracoesDisco = {
    maintainAspectRatio: false,
    responsive: true,
    animation: exibiuGraficoDisco ? false : { duration: 1500 },
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

  exibiuGraficoDisco = true;

  return configuracoesDisco;
}

function obterDadosGraficos() {

  dadosRam = {

    datasets: [{
      data: [ramIndisponivel, ramDisponivel],
      backgroundColor: [corIndisponivelRam, corDisponivelRam]
    }],
    labels: ["Utilizado (%)", "Disponível (%)"]
  };

  dadosCpu = {

    datasets: [{
      data: [cpuIndisponivel, cpuDisponivel],
      backgroundColor: [corIndisponivelCpu, corDisponivelCpu]
    }],
    labels: ["Utilizado (%)", "Disponível (%)"]
  };

  dadosDisco = {

    datasets: [{
      data: [discoIndisponivel, discoDisponivel],
      backgroundColor: [corIndisponivelDisco, corDisponivelDisco]
    }],
    labels: ["Utilizado (%)", "Disponível (%)"]
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
          // que gerou na consulta ao banco de dadosRam

          //dadosRam.datasets[0].data.push(registro.ramAtual);
          ramIndisponivel = parseFloat(registro.ramAtual).toFixed(2);
          ramDisponivel = parseFloat(100 - ramIndisponivel).toFixed(2);
          document.getElementById('dadosRamIndisponivel').innerHTML = ramIndisponivel;
          document.getElementById('dadosRamDisponivel').innerHTML = ramDisponivel;

          cpuIndisponivel = parseFloat(registro.cpuAtual).toFixed(2);
          cpuDisponivel = parseFloat(100 - cpuIndisponivel).toFixed(2);
          document.getElementById('dadosCpuIndisponivel').innerHTML = cpuIndisponivel;
          document.getElementById('dadosCpuDisponivel').innerHTML = cpuDisponivel;

          discoIndisponivel = parseFloat(registro.discoAtual).toFixed(2);
          discoDisponivel = parseFloat(100 - discoIndisponivel).toFixed(2);
          document.getElementById('dadosDiscoIndisponivel').innerHTML = discoIndisponivel;
          document.getElementById('dadosDiscoDisponivel').innerHTML = discoDisponivel;


          if (ramDisponivel <= 20) {
            corDisponivelRam = corCritico;
          } else {
            corDisponivelRam = corInicial;
          }

          if (cpuDisponivel <= 20) {
            corDisponivelCpu = corCritico;
          } else {
            corDisponivelCpu = corInicial;
          }

          if (discoDisponivel <= 20) {
            corDisponivelDisco = corCritico;
          } else {
            corDisponivelDisco = corInicial;
          }

        }
        console.log(JSON.stringify(dadosRam));
        console.log(JSON.stringify(dadosCpu));
        console.log(JSON.stringify(dadosDisco));


        plotarGraficoRam(dadosRam);
        plotarGraficoCpu(dadosCpu);
        plotarGraficoDisco(dadosDisco);
      });
    } else {
      console.error('Nenhum dado encontrado ou erro na API');
    }
  })
    .catch(function (error) {
      console.error(`Erro na obtenção dos dados p/ gráfico: ${error.message}`);
    });
}



function plotarGraficoRam(dadosRam) {
  console.log('iniciando plotagem do gráfico...');

  var ctx = document.getElementById("myRamChart");
  if (window.myChartRam) {
    window.myChartRam.data = dadosRam;
    window.myChartRam.update();
    exibiuGraficoRam = true;
  } else {
    window.myChartRam = new Chart(ctx, {
      type: 'doughnut',
      data: dadosRam,
      options: configurarGraficoRam()
    });
  }
}

function plotarGraficoCpu(dadosCpu) {
  console.log('iniciando plotagem do gráfico...');

  var ctx = document.getElementById("myCpuChart");
  if (window.myChartCpu) {
    window.myChartCpu.data = dadosCpu;
    window.myChartCpu.update();
    exibiuGraficoCpu = true;
  } else {
    window.myChartCpu = new Chart(ctx, {
      type: 'doughnut',
      data: dadosCpu,
      options: configurarGraficoCpu()
    });
  }
}

function plotarGraficoDisco(dadosDisco) {
  console.log('iniciando plotagem do gráfico...');

  var ctx = document.getElementById("myDiskChart");
  if (window.myChartDisk) {
    window.myChartDisk.data = dadosDisco;
    window.myChartDisk.update();
    exibiuGraficoDisco = true;
  } else {
    window.myChartDisk = new Chart(ctx, {
      type: 'doughnut',
      data: dadosDisco,
      options: configurarGraficoDisco()
    });
  }
}

