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
            text: 'You Index',
            x: -20 //center
        }},
        /*subtitle: {{
            text: 'Source: WorldClimate.com',
            x: -20
        }},*/
        xAxis: {{
            categories: [{fn:string-join(for $x in $doc/ChartData/Labels/Label return concat("'", string($x),"'"),",")}]
        }},
        yAxis: {{
            title: {{
                text: 'Index'
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
		string-join(
		for $ds at $dsp in $doc/ChartData/DataSets/DataSet
		return concat(
			'{
			name: "',
			$ds/Label/text(),
			'",
			data: [',
			string-join(for $x in $ds/Values/Value return string($x),","),
			']
		}'
			),
		","
		)
		}]
    }});
}});
		</script>
	</head>
	<body>
<script src="http://localhost:8080/HighCharts/Highcharts-4.0.4/js/highcharts.js">//keep this</script>
<script src="http://localhost:8080/HighCharts/Highcharts-4.0.4/js/modules/exporting.js">//keep that</script>
<div id="container" style="min-width: 310px; height: 350px; margin: 0 auto"></div>
	</body>
</html>