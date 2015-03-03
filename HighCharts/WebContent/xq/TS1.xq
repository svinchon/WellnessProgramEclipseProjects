declare variable $doc external;
let $date := $doc/hr_stats/last_four_weeks_history/item[1]/date,
$day := substring($date,9,2),
$month := xs:int(substring($date,6,2)) - 1
return
<html>
	<head>
		<test></test>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<title>Highcharts Example</title>
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js">//keep it</script>
		<script type="text/javascript" src="http://localhost:8080/HighCharts/jQuery/jquery.min.js">//keep it</script>
		<script type="text/javascript">
$(function () {{
    $('#container').highcharts({{
        chart: {{
            zoomType: 'x'
      	}},
        title: {{
            text: 'HR Statistics'
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
            pointStart: Date.UTC(2016, {$month}, {$day}),
            data: [
				{string-join(for $x in $doc/hr_stats/last_four_weeks_history/item/total_enrollment_requests return string($x),",")}
            ]
        }},{{
            type: 'spline',
            name: 'Weekly Runs',
            pointInterval: 24 * 3600 * 1000,
            pointStart: Date.UTC(2016, {$month}, {$day}),
            data: [
				{string-join(for $x in $doc/hr_stats/last_four_weeks_history/item/day_runs return string($x),",")}
            ]
        }}]
    }});
}});
		</script>
	</head>
	<body>
<script src="http://localhost:8080/HighCharts/Highcharts-4.0.4/js/highcharts.js">//keep this</script>
<script src="http://localhost:8080/HighCharts/Highcharts-4.0.4/js/modules/exporting.js">//keep that</script>
<div id="container" style="min-width: 310px; height: 300px; margin: 0 auto"></div>
	</body>
</html>