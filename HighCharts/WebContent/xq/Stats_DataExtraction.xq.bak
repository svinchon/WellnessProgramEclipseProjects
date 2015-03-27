let $input_date := xs:date(substring(xs:string(current-date()),1,10))
let $start_date := $input_date - xs:dayTimeDuration("P28D")
let $members := doc('/Members.xml')/members/member
let $daily_member_data := doc('/xPressionHelper/FromDailyUpdates')/daily_data/daily_member_data
let $audit_trail := doc('/AuditTrail.xml')/audit_trail
let $program_config := doc('ProgramConfiguration.xml')/program_configuration
return
<stats>
<date>{$input_date}</date>
{$program_config/available_devices_count}
<member_count>{count($members)}</member_count>
<enrollment_requests_count>{count($audit_trail/event[./type = "registerNewUser"])}</enrollment_requests_count>
<last_four_weeks_history>
<items>
{
for $day_offset in (1 to 28)
let $current_date := xs:date($start_date) + xs:dayTimeDuration(concat("P", $day_offset, "D"))
let $daily_index_values := $daily_member_data/daily_index_value[xs:date(../date_stamp) = $current_date]
let $c0 := count($daily_index_values)
let $a0 := avg($daily_index_values)
return
<item>
<date>{$current_date}</date>
<day_enrollment_requests>{count($audit_trail/event[xs:date(substring(./time/text(), 1, 10)) = $current_date and ./type = "registerNewUser"])}</day_enrollment_requests>
<total_enrollment_requests>{count($audit_trail/event[xs:date(substring(./time/text(), 1, 10)) <= $current_date and ./type = "registerNewUser"])}</total_enrollment_requests>
<day_runs>{count($audit_trail/event[xs:date(substring(./time/text(), 1, 10)) = $current_date and ./type = "initiateXBatchJob"])}</day_runs>
<sample_count>{count($daily_member_data/daily_index_value[xs:date(../date_stamp) = $current_date])}</sample_count>
<member_count>{count($members[xs:date(enrollment_date) <= $current_date])}</member_count>
<avg>{if ($c0>0) then $a0 else 0}</avg>
<teams>
{
for $team in distinct-values($members/team)
let $team_daily_index_values := for $m in $members where $m/team = $team return $daily_member_data/daily_index_value[xs:date(../date_stamp) = $current_date and ../member_id = $m/badge_number]
let $c1 := count($team_daily_index_values)
let $a1 := avg($team_daily_index_values)
return
<team>
<team_name>{$team}</team_name>
<team_sample_count>{$c1}</team_sample_count>
<team_avg>{if ($c1>0) then $a1 else 0}</team_avg>
</team>
}
</teams>
</item>
}
</items>
</last_four_weeks_history>
</stats>