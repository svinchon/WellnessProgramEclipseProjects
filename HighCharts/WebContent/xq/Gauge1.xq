declare variable $doc external;
let $date := $doc/hr_stats/last_four_weeks_history/item[1]/date,
$day := substring($date,9,2),
$month := xs:int(substring($date,6,2)) - 1,
$enrollment_rate1 := xs:int($doc/hr_stats/member_count) div 20,
$enrollment_rate := $enrollment_rate1 * 100

return
<html>
	<head>
		<test></test>
		<title>Highcharts Example</title>
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js">//keep it</script>
		<script type="text/javascript" src="http://localhost:8080/HighCharts/jQuery/jquery.min.js">//keep it</script>
		<script type="text/javascript">
$(function () {{
    $('#container').highcharts({{
        chart: {{
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
}});
		</script>
	</head>
	<body>
<script src="http://localhost:8080/HighCharts/Highcharts-4.0.4/js/highcharts.js">//keep this</script>
<script src="http://code.highcharts.com/highcharts-more.js">//keep this</script>
<script src="http://localhost:8080/HighCharts/Highcharts-4.0.4/js/modules/exporting.js">//keep that</script>
<div id="container" style="min-width: 310px; height: 300px; margin: 0 auto"></div>
	</body>
</html>