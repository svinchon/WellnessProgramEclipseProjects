declare variable $doc external;
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<title>Highcharts Example</title>
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js">//keep it</script>
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
            categories: [{fn:string-join(for $x in $doc/LineChart/Labels/Label return concat("'", string($x),"'"),",")}]
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
		for $ds at $dsp in $doc/LineChart/DataSets/DataSet
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
<script src="http://localhost:18080/HighCharts/Highcharts-4.0.4/js/highcharts.js">//keep this</script>
<script src="http://localhost:18080/HighCharts/Highcharts-4.0.4/js/modules/exporting.js">//keep that</script>
<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
	</body>
</html>(: Stylus Studio meta-information - (c) 2004-2009. Progress Software Corporation. All rights reserved.

<metaInformation>
	<scenarios>
		<scenario default="yes" name="Scenario1" userelativepaths="yes" externalpreview="no" useresolver="yes" url="..\..\..\..\..\GIT\WellnessProgramXPression\xDWTemplates\LineChartData.xml" outputurl="" processortype="datadirect" tcpport="0"
		          profilemode="0" profiledepth="" profilelength="" urlprofilexml="" commandline="" additionalpath="" additionalclasspath="" postprocessortype="none" postprocesscommandline="" postprocessadditionalpath="" postprocessgeneratedext="" host=""
		          port="0" user="" password="" validateoutput="no" validator="internal" customvalidator="">
			<advancedProperties name="DocumentURIResolver" value=""/>
			<advancedProperties name="CollectionURIResolver" value=""/>
			<advancedProperties name="ModuleURIResolver" value=""/>
		</scenario>
	</scenarios>
	<MapperMetaTag>
		<MapperInfo srcSchemaPathIsRelative="yes" srcSchemaInterpretAsXML="no" destSchemaPath="" destSchemaRoot="" destSchemaPathIsRelative="yes" destSchemaInterpretAsXML="no"/>
		<MapperBlockPosition></MapperBlockPosition>
		<TemplateContext></TemplateContext>
		<MapperFilter side="source"></MapperFilter>
	</MapperMetaTag>
</metaInformation>
:)