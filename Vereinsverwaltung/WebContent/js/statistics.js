/**
 * @author tobi + kubi
 */
var line = document.getElementById("lineChart").getContext('2d');
var data2018 = document.getElementById("2018Data").value;
var data2017 = document.getElementById("2017Data").value;
var data2016 = document.getElementById("2016Data").value;
var data2015 = document.getElementById("2015Data").value;
var data2014 = document.getElementById("2014Data").value;
var barChart = new Chart(line, {
    type: 'line',
data: {
        labels: ["2014", "2015", "2016", "2017", "2018"],
        datasets: [{
            data: [data2014, data2015, data2016, data2017, data2018],
            backgroundColor: [
                'rgba(255, 99, 132, 0.7)',
            ],
        }]
    },
    options: {
        scales: {
            yAxes: [{
                ticks: {
                    beginAtZero:true
                }
            }]
        }
    }
});

var donut = document.getElementById("donutChart").getContext('2d');
var femalePercentage = document.getElementById("femaleData").value;
var neutralPercentage = document.getElementById("neutralData").value;
var malePercentage = document.getElementById("maleData").value;
var donutChart = new Chart(donut, {
    type: 'doughnut',
    data: {
        labels: ["Female", "Neutral", "Male"],
        datasets: [{
            label: '# of Votes',
            data: [femalePercentage, neutralPercentage, malePercentage],
            backgroundColor: [
                'rgba(255, 99, 132,0.7 )',
                'rgba(0, 255, 0,0.3)',
                'rgba(0, 0, 255,0.3)'
            ],
        }]
    },
});
