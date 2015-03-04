let $input_date := xs:date(substring(xs:string(current-date()),1,10)), 
$start_date := $input_date - xs:dayTimeDuration("P28D"), 
$audit_trail := doc('/AuditTrail.xml')/audit_trail,
$members := doc('/Members.xml')/members
let $source := doc('/xPressionHelper/FromDailyUpdates/')
return
<hr_stats>
<date>{$input_date}</date>
<start_date>{$start_date}</start_date>
<member_count>{count($members/member)}</member_count>
<enrollment_requests_count>{count($audit_trail/event[./type = "registerNewUser"])}</enrollment_requests_count>
<report_runs_count>{count($audit_trail/event[./type = "initiateXBatchJob"])}</report_runs_count>
<last_four_weeks_history>
{
for $day_offset in (1 to 28)
let $current_date := xs:date($start_date) + xs:dayTimeDuration(concat("P", $day_offset, "D"))
return
<item>
<date>{$current_date}</date>
<day_runs>{count($audit_trail/event[xs:date(substring(./time/text(), 1, 10)) = $current_date and ./type = "initiateXBatchJob"])}</day_runs>
<day_enrollment_requests>{count($audit_trail/event[xs:date(substring(./time/text(), 1, 10)) = $current_date and ./type = "registerNewUser"])}</day_enrollment_requests>
<total_enrollment_requests>{count($audit_trail/event[xs:date(substring(./time/text(), 1, 10)) <= $current_date and ./type = "registerNewUser"])}</total_enrollment_requests>
<index_count>{count($source/daily_data/daily_member_data[xs:date(./date_stamp) = $current_date]/daily_index_value)}</index_count>
<index_avg>{
if (count($source/daily_data/daily_member_data[xs:date(./date_stamp) = $current_date]/daily_index_value) > 0)
then avg($source/daily_data/daily_member_data[xs:date(./date_stamp) = $current_date]/daily_index_value)
else 0
}</index_avg>
</item>
}
</last_four_weeks_history>
</hr_stats>