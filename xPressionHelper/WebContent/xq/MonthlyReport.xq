let $current_day := xs:date("2015-03-04")
let $first_day := $current_day - xs:dayTimeDuration("P28D")
let $source := doc('/xPressionHelper/FromDailyUpdates/')
let $award_threshold := 300
let $award_days_above_threshold_required := 1
return
<documents>
{
for $m in doc('/Members.xml')/members/member
let $days_above_threshold := count(distinct-values($source/daily_data/daily_member_data[./member_id = $m/badge_number and xs:date(./date_stamp) > $first_day and xs:date(./date_stamp) < $current_day and ./daily_index_value > $award_threshold]/date_stamp))
return
	<document>
		<document_type>Monthly</document_type>
		<badge_number>{$m/badge_number/text()}</badge_number>
		<email>{$m/email/text()}</email>
		<first_name>{$m/first_name/text()}</first_name>
		<last_name>{$m/last_name/text()}</last_name>
		<gender>{$m/gender/text()}</gender>
		<update_date>{$current_day}</update_date>
		<first_date>{$first_day}</first_date>
		<award_threshold>{$award_threshold}</award_threshold>
		<days_above_threshold>{$days_above_threshold}</days_above_threshold>
		<award>{$days_above_threshold > $ award_days_above_threshold_required}</award>
		<index_history_nvp>
	{
	fn:string-join(
		for $day_offset in (1 to 28)
		let $current_date := $first_day + xs:dayTimeDuration(concat("P", $day_offset, "D"))
		let $current_date_extract := xs:date($source/daily_data/daily_member_data[
			./member_id = $m/badge_number
			and
			xs:date(./date_stamp/text()) = xs:date($current_date)
		][1]/date_stamp/text())
		let $current_date_day := xs:date($current_date)
		let $current_value := $source/daily_data/daily_member_data[
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
				for $day_offset in (1 to 28)
				let $current_date := $first_day + xs:dayTimeDuration(concat("P", $day_offset, "D"))
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
				for $day_offset in (1 to 28)
				let $current_date := $first_day + xs:dayTimeDuration(concat("P", $day_offset, "D"))
				let $current_date_extract := xs:date($source/daily_data/daily_member_data[
					./member_id = $m/badge_number
					and
					xs:date(./date_stamp/text()) = xs:date($current_date)
				][1]/date_stamp/text())
				let $current_date_day := xs:date($current_date)
				let $current_value := $source/daily_data/daily_member_data[
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
		concat(
			'<categories><title>Date</title><values>',
			fn:string-join(
				for $day_offset in (1 to 28)
				let $current_date := $first_day + xs:dayTimeDuration(concat("P", $day_offset, "D"))
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
				for $day_offset in (1 to 28)
				let $current_date := $first_day + xs:dayTimeDuration(concat("P", $day_offset, "D"))
				let $current_date_extract := xs:date($source/daily_data/daily_member_data[
					./member_id = $m/badge_number
					and
					xs:date(./date_stamp/text()) = xs:date($current_date)
				][1]/date_stamp/text())
				let $current_date_day := xs:date($current_date)
				let $current_value := $source/daily_data/daily_member_data[
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