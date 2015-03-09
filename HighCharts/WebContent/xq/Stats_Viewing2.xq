declare variable $doc external;
let $date := $doc/stats/last_four_weeks_history/items/item[1]/date/text(),
$day := substring($date,9,2),
$month := xs:int(substring($date,6,2)) - 1,
$year := xs:int(substring($date,1,4)),
$enrollment_rate1 := xs:int($doc/stats/member_count) div xs:int($doc/stats/available_devices_count),
$enrollment_rate := $enrollment_rate1 * 100

return
<html>
	<head>
		<test></test>
		<title>Charts</title>
		<script type="text/javascript" src="http://xcp:8080/HighCharts/jQuery/jquery.min.js">//keep it</script>
		<script type="text/javascript">
$(function () {{
    $(document).ready(function() {{
    var C3 = new Highcharts.Chart({{
        chart: {{
			renderTo: 'container3',
            type: 'gauge',
            plotBackgroundColor: null,
            plotBackgroundImage: null,
            plotBorderWidth: 0,
            plotShadow: false,
			width: 250,
			height: 250
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
}});
		</script>
	</head>
	<body>
<script src="http://xcp:8080/HighCharts/Highcharts-4.0.4/js/highcharts.js">//keep this</script>
<script src="http://xcp:8080/HighCharts/Highcharts-4.0.4/js/highcharts-more.js">//keep this</script>
<script src="http://xcp:8080/HighCharts/Highcharts-4.0.4/js/modules/exporting.js">//keep that</script>
<div id="content">
   <div style="display: table-row">
		<div id="container3" style="display: table-cell;">www</div>
	</div>
</div>
	</body>
</html>