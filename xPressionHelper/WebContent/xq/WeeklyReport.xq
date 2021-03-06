let $source1 := doc('/xPressionHelper/FromWeeklyUpdates/')
let $source2 := doc('/xPressionHelper/FromDailyUpdates/')
let $award_threshold := doc('/ProgramConfiguration.xml')/program_configuration/awards_threshold/text()
return
<documents>
{
for $m in /members/member
for $r in $source1/weekly_data/weekly_member_data[./member_id = $m/badge_number][last()]
return
	<document>
		<document_type>Weekly</document_type>
		<badge_number>{$m/badge_number/text()}</badge_number>
		<email>{$m/email/text()}</email>
		<first_name>{$m/first_name/text()}</first_name>
		<last_name>{$m/last_name/text()}</last_name>
		<gender>{$m/gender/text()}</gender>
	{$r/week_start_date}
	{$r/recommendations}
		<index_history_nvp>
	{
	fn:string-join(
		for $day_offset in (1 to 7)
		let $current_date := xs:date($r/week_start_date) + xs:dayTimeDuration(concat("P", $day_offset, "D"))
		let $current_date_extract := xs:date($source2/daily_data/daily_member_data[
			./member_id = $m/badge_number
			and
			xs:date(./date_stamp/text()) = xs:date($current_date)
		][1]/date_stamp/text())
		let $current_date_day := xs:date($current_date)
		let $current_value := $source2/daily_data/daily_member_data[
			./member_id = $m/badge_number
			and
			xs:date(./date_stamp/text()) = xs:date($current_date)
		][1]/daily_index_value[1]/text()
		return 
			if ($current_date_day=$current_date_extract)
			then concat($current_date_day, '=', $current_value)
			else concat($current_date_day, '=0')
		,
		';'
	)
	}
		</index_history_nvp>
		<index_history_csv>
	{
	concat(
		concat(
			'Categories',
			';',
			fn:string-join(
				for $day_offset in (1 to 7)
				let $current_date := xs:date($r/week_start_date) + xs:dayTimeDuration(concat("P", $day_offset, "D"))
				let $current_date_day := xs:string($current_date)
				return $current_date_day
				,
				';'
			),
			'#'
		),
		concat(
			'Serie 1',
			';',
			fn:string-join(
				for $day_offset in (1 to 7)
				let $current_date := xs:date($r/week_start_date) + xs:dayTimeDuration(concat("P", $day_offset, "D"))
				let $current_date_extract := xs:date($source2/daily_data/daily_member_data[
					./member_id = $m/badge_number
					and
					xs:date(./date_stamp/text()) = xs:date($current_date)
				][1]/date_stamp/text())
				let $current_date_day := xs:date($current_date)
				let $current_value := $source2/daily_data/daily_member_data[
					./member_id = $m/badge_number
					and
					xs:date(./date_stamp/text()) = xs:date($current_date)
				][1]/daily_index_value[1]/text()
				return 
					if ($current_date_day=$current_date_extract)
					then $current_value
					else "0"
				,
				';'
			)
		)
	)
	}
		</index_history_csv>
		<index_history_xml>
	{
	concat(
		'<chart_data>',
		'<horizontal_marker>',
		'<value>', $award_threshold, '</value>',
		'<line_style></line_style>',
		'<line_color>FFFF00</line_color>',
		'<label>AT</label>',
		'</horizontal_marker>',
		concat(
			'<categories><title>Date</title><values>',
			fn:string-join(
				for $day_offset in (1 to 7)
				let $current_date := xs:date($r/week_start_date) + xs:dayTimeDuration(concat("P", $day_offset, "D"))
				let $current_date_day := xs:string($current_date)
				return concat('<value>', $current_date_day,'</value>')
				,
				''
			),
			'</values></categories>'
		),
		'<series>',
		concat(
			'<serie><title>Index</title><values>',
			fn:string-join(
				for $day_offset in (1 to 7)
				let $current_date := xs:date($r/week_start_date) + xs:dayTimeDuration(concat("P", $day_offset, "D"))
				let $current_date_extract := xs:date($source2/daily_data/daily_member_data[
					./member_id = $m/badge_number
					and
					xs:date(./date_stamp/text()) = xs:date($current_date)
				][1]/date_stamp/text())
				let $current_date_day := xs:date($current_date)
				let $current_value := $source2/daily_data/daily_member_data[
					./member_id = $m/badge_number
					and
					xs:date(./date_stamp/text()) = xs:date($current_date)
				][1]/daily_index_value[1]/text()
				return 
					if ($current_date_day=$current_date_extract)
					then concat('<value>', $current_value, '</value>')
					else "<value>0</value>"
				,
				''
			),
			'</values></serie>'
		),
		'</series>',
		'</chart_data>'
	)
	}
		</index_history_xml>
	</document>
}
</documents>