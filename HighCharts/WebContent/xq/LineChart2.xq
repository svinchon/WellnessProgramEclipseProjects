declare variable $doc external;
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<title>Highcharts Example</title>
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js">//keep it</script>
		<script type="text/javascript" src="http://localhost:8080/HighCharts/jQuery/jquery.min.js">//keep it</script>
		<script type="text/javascript">
$(function () {{
    $('#container').highcharts({{
        title: {{
            text: 'HR Statistics',
            x: -20 //center
        }},
        /*subtitle: {{
            text: 'Source: WorldClimate.com',
            x: -20
        }},*/
        xAxis: {{
            categories: [{fn:string-join(for $x in $doc/hr_stats/last_four_weeks_history/item/date return concat("'", string($x),"'"),",")}]
			,labels: {{
	            rotation: 300
	            //,y:40                
        	}},
        }},
        yAxis: {{
            title: {{
                text: 'count'
            }},
            plotLines: [{{
                value: 0,
                width: 1,
                color: '#808080'
            }}]
        }},
        tooltip: {{
            valueSuffix: ''
        }},
        legend: {{
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        }},
        series: [{
			concat(
			'{
			name: "',
			'enrollment request total',
			'",
			data: [',
			string-join(for $x in $doc/hr_stats/last_four_weeks_history/item/total_enrollment_requests return string($x),","),
			']
			}, {
			name: "',
			'day runs',
			'",
			data: [',
			string-join(for $x in $doc/hr_stats/last_four_weeks_history/item/day_runs return string($x),","),
			']
			}'
			)
		}]
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