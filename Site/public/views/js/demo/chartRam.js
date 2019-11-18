let ramIndisponivel=81;
let ramDisponivel = 100 - ramIndisponivel;
let corInicial =  "#1cc88a";
let corCritico = "#e74a3b";

let corDisponivel = "#d19532";
let corIndisponivel = "#858796";

if(ramIndisponivel>80){
  corDisponivel = corCritico;
} else {
  corDisponivel = corInicial;
}

function atualizarGrafico() {
  obterDadosGrafico();
  obterDadosGrafico2();
  setTimeout(atualizarGrafico, 3000);
}

Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#858796';

// Pie Chart Example
var ctx = document.getElementById("myRamChart");
var myRamChart = new Chart(ctx, {
  type: 'doughnut',
  data: {
    labels: ["Disponível", "Indisponível"],
    datasets: [{
      data: [ramDisponivel, ramIndisponivel],
      backgroundColor: [corDisponivel, corIndisponivel]
    }],
  },
  options: {
    maintainAspectRatio: false,
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
  },
});
