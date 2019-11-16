let ramObtida=81;
let corInicial =  "#1cc88a";
let corCritico = "#e74a3b";

let corDisponivel = "#d19532";
let corIndisponivel = "#858796";

if(ramObtida>80){
  corDisponivel = corCritico;
} else {
  corDisponivel = corInicial;
}

// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#858796';

// Pie Chart Example
var ctx = document.getElementById("myRamChart");
var myRamChart = new Chart(ctx, {
  type: 'doughnut',
  data: {
    labels: ["Disponível", "Indisponível"],
    datasets: [{
      data: [10, 90],
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


