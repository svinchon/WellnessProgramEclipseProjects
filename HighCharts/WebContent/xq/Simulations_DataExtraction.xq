let $threshold := 350
let $needed_days := 5
let $end_date := xs:date("2015-03-06")
let $start_date := $end_date - xs:dayTimeDuration("P28D")
let $possibly_awarded_members := for $m in doc('Members.xml')/members/member
where count
  (
    doc('/xPressionHelper/FromDailyUpdates/')/daily_data/daily_member_data[
      ./member_id = $m/badge_number
      and
      ./daily_index_value > $threshold
	  and
	  xs:date(./date_stamp) >= $start_date
	  and
	  xs:date(./date_stamp) <= $end_date
    ]
  ) >= $needed_days
return $m
return 
<simulations>
  <simulation>
    <threshold>{$threshold}</threshold>
    <needed_days>{$needed_days}</needed_days>
    <end_date>{$end_date}</end_date>
    <start_date>{$start_date}</start_date>
    <possibly_awarded_members_count>{count($possibly_awarded_members)}</possibly_awarded_members_count>
    <needed_days_impact_chart>
{
for $nd in (1 to 10) (:3, 5, 10, 15):)
let $possibly_awarded_members2 := for $m in doc('Members.xml')/members/member
where count
  (
    doc('/xPressionHelper/FromDailyUpdates/')/daily_data/daily_member_data[
      ./member_id = $m/badge_number
      and
      ./daily_index_value > $threshold
	  and
	  xs:date(./date_stamp) >= $start_date
	  and
	  xs:date(./date_stamp) <= $end_date
    ]
  ) >= $nd
return $m
return <item><needed_days>{$nd}</needed_days><pam>{count($possibly_awarded_members2)}</pam></item>
}
    </needed_days_impact_chart>
    <threshold_impact_chart>
{
for $s in (1 to 14)
let $t := $s*50
let $possibly_awarded_members3 := for $m in doc('Members.xml')/members/member
where count
  (
    doc('/xPressionHelper/FromDailyUpdates/')/daily_data/daily_member_data[
      ./member_id = $m/badge_number
      and
      ./daily_index_value > $t
 	  and
	  xs:date(./date_stamp) >= $start_date
	  and
	  xs:date(./date_stamp) <= $end_date
   ]
  ) >= $needed_days
return $m
return <item><threshold>{$t}</threshold><pam>{count($possibly_awarded_members3)}</pam></item>
}
    </threshold_impact_chart>
  </simulation>
</simulations>