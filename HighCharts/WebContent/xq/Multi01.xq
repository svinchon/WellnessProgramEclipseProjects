declare variable $doc external;
let $date := $doc/hr_stats/last_four_weeks_history/item[1]/date,
$day := substring($date,9,2),
$month := xs:int(substring($date,6,2)) - 1,
$year := xs:int(substring($date,1,4)),
$enrollment_rate1 := xs:int($doc/hr_stats/member_count) div 20,
$enrollment_rate := $enrollment_rate1 * 100

return
<html>
	<head>
		<test></test>
		<title>More Charts</title>
		<script type="text/javascript" src="http://xcp:8080/HighCharts/jQuery/jquery.min.js">//keep it</script>
		<script type="text/javascript">
$(function () {{
    $(document).ready(function() {{
    var C1 = new Highcharts.Chart({{
        chart: {{
			renderTo: 'container1',
            type: 'gauge',
            plotBackgroundColor: null,
            plotBackgroundImage: null,
            plotBorderWidth: 0,
            plotShadow: false
        }},

        title: {{
            text: 'Enrollometer'
        }},

        pane: {{
            startAngle: -150,
            endAngle: 150,
            background: [{{
                backgroundColor: {{
                    linearGradient: {{ x1: 0, y1: 0, x2: 0, y2: 1 }},
                    stops: [
                        [0, '#FFF'],
                        [1, '#333']
                    ]
                }},
                borderWidth: 0,
                outerRadius: '109%'
            }}, {{
                backgroundColor: {{
                    linearGradient: {{ x1: 0, y1: 0, x2: 0, y2: 1 }},
                    stops: [
                        [0, '#333'],
                        [1, '#FFF']
                    ]
                }},
                borderWidth: 1,
                outerRadius: '107%'
            }}, {{
                backgroundColor: '#DDD',
                borderWidth: 0,
                outerRadius: '105%',
                innerRadius: '103%'
            }}]
        }},

        // the value axis
        yAxis: {{
            min: 0,
            max: 100,

            minorTickInterval: 'auto',
            minorTickWidth: 1,
            minorTickLength: 10,
            minorTickPosition: 'inside',
            minorTickColor: '#666',

            tickPixelInterval: 30,
            tickWidth: 2,
            tickPosition: 'inside',
            tickLength: 10,
            tickColor: '#666',
            labels: {{
                step: 2,
                rotation: 'auto'
            }},
            title: {{
                text: 'km/h'
            }},
            plotBands: [{{
                from: 50,
                to: 100,
                color: '#55BF3B' // green
            }}, {{
                from: 20,
                to: 50,
                color: '#DDDF0D' // yellow
            }}, {{
                from: 0,
                to: 20,
                color: '#DF5353' // red
            }}]
        }},
        series: [{{
            name: 'Enrollment',
            data: [{$enrollment_rate}],
            tooltip: {{
                valueSuffix: ' %'
            }}
        }}]
	}});    
    var C2 = new Highcharts.Chart({{
        chart: {{
			renderTo: 'container2',
            zoomType: 'x'
      	}},
        title: {{
            text: 'Counts'
        }},
        subtitle: {{
            text: document.ontouchstart === undefined ?
                    'Click and drag in the plot area to zoom in' :
                    'Pinch the chart to zoom in'
        }},
        xAxis: {{
            type: 'datetime',
            minRange: 5 * 24 * 3600000 // fourteen days
        }},
        yAxis: {{
            min: 0,
            title: {{
                text: 'count'
            }}
        }},
        legend: {{
            enabled: false
        }},
        plotOptions: {{
            area: {{
                fillColor: {{
                    linearGradient: {{ x1: 0, y1: 0, x2: 0, y2: 1}},
                    stops: [
                        [0, Highcharts.getOptions().colors[0]],
                        [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
                    ]
                }},
                marker: {{
                    radius: 2
                }},
                lineWidth: 1,
                states: {{
                    hover: {{
                        lineWidth: 1
                    }}
                }},
                threshold: null
            }}
        }},

        series: [{{
            type: 'spline',
            name: 'Enrollment Request Total',
            pointInterval: 24 * 3600 * 1000,
            pointStart: Date.UTC({$year}, {$month}, {$day}),
            data: [
				{string-join(for $x in $doc/hr_stats/last_four_weeks_history/item/total_enrollment_requests return string($x),",")}
            ]
        }},{{
            type: 'spline',
            name: 'Weekly Runs',
            pointInterval: 24 * 3600 * 1000,
            pointStart: Date.UTC({$year}, {$month}, {$day}),
            data: [
				{string-join(for $x in $doc/hr_stats/last_four_weeks_history/item/day_runs return string($x),",")}
            ]
        }},{{
            type: 'spline',
            name: 'Index Values',
            pointInterval: 24 * 3600 * 1000,
            pointStart: Date.UTC({$year}, {$month}, {$day}),
            data: [
				{string-join(for $x in $doc/hr_stats/last_four_weeks_history/item/index_count return string($x),",")}
            ]
        }}]
    }});
    var C3 = new Highcharts.Chart({{
        chart: {{
			renderTo: 'container3',
            zoomType: 'x'
      	}},
        title: {{
            text: 'Index Average'
        }},
        subtitle: {{
            text: document.ontouchstart === undefined ?
                    'Click and drag in the plot area to zoom in' :
                    'Pinch the chart to zoom in'
        }},
        xAxis: {{
            type: 'datetime',
            minRange: 5 * 24 * 3600000 // fourteen days
        }},
        yAxis: {{
            min: 0,
            title: {{
                text: 'count'
            }}
        }},
        legend: {{
            enabled: false
        }},
        plotOptions: {{
            area: {{
                fillColor: {{
                    linearGradient: {{ x1: 0, y1: 0, x2: 0, y2: 1}},
                    stops: [
                        [0, Highcharts.getOptions().colors[0]],
                        [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
                    ]
                }},
                marker: {{
                    radius: 2
                }},
                lineWidth: 1,
                states: {{
                    hover: {{
                        lineWidth: 1
                    }}
                }},
                threshold: null
            }}
        }},

        series: [{{
            type: 'area',
            name: 'Index Average',
            pointInterval: 24 * 3600 * 1000,
            pointStart: Date.UTC({$year}, {$month}, {$day}),
            data: [
				{string-join(for $x in $doc/hr_stats/last_four_weeks_history/item/index_avg return string($x),",")}
            ]
        }}]
    }});
	}});
}});
		</script>
	</head>
	<body>
<script src="http://xcp:8080/HighCharts/Highcharts-4.0.4/js/highcharts.js">//keep this</script>
<script src="http://xcp:8080/HighCharts/Highcharts-4.0.4/js/highcharts-more.js">//keep this</script>
<script src="http://xcp:8080/HighCharts/Highcharts-4.0.4/js/modules/exporting.js">//keep that</script>
<div id="content">
   <div style="">
		<div id="container1" style="height: 200px; ">www</div>
	</div>
	<div>
		<div id="container2" style="height: 200px; ">www</div>
	</div>
	<div>
		<div id="container3" style="height: 200px;">www</div>
	</div>
</div>
	</body>
</html>