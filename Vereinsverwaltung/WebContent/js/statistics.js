var bar = document.getElementById("barChart").getContext('2d');
var barChart = new Chart(bar, {
    type: 'bar',
data: {
        labels: ["Red", "Blue", "Yellow", "Green", "Purple", "Orange"],
        datasets: [{
            label: '# of Votes',
            data: [12, 19, 3, 5, 2, 3],
            backgroundColor: [
                'rgba(255, 99, 132,0.7 )',
                'rgba(54, 162, 235,0.7 )',
                'rgba(255, 206, 86,0.7 )',
                'rgba(75, 192, 192,0.7 )',
                'rgba(153, 102, 255,0.7)',
                'rgba(255, 159, 64,0.7)'
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
var donutChart = new Chart(donut, {
    type: 'doughnut',
    data: {
        labels: ["Red", "Blue", "Yellow", "Green", "Purple", "Orange"],
        datasets: [{
            label: '# of Votes',
            data: [12, 19, 3, 5, 2, 3],
            backgroundColor: [
                'rgba(255, 99, 132,0.7 )',
                'rgba(54, 162, 235,0.7 )',
                'rgba(255, 206, 86,0.7 )',
                'rgba(75, 192, 192,0.7 )',
                'rgba(153, 102, 255,0.7)',
                'rgba(255, 159, 64,0.7)'
            ],
        }]
    },
});
