let $threshold := 350
let $needed_days := 5
let $end_date := xs:date(substring(xs:string(current-date()),1,10))
let $start_date := $end_date - xs:dayTimeDuration("P28D")
(:let $possibly_awarded_members := for $m in doc('Members.xml')/members/member:)
(:let $members = doc('Members.xml')/members/member:)
let $members := /members/member
let $possibly_awarded_members := for $m in $members
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
let $possibly_awarded_members2 := for $m in $members
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
let $possibly_awarded_members3 := for $m in $members
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
</simulations>(: Stylus Studio meta-information - (c) 2004-2009. Progress Software Corporation. All rights reserved.

<metaInformation>
	<scenarios>
		<scenario default="yes" name="Scenario1" userelativepaths="yes" externalpreview="no" useresolver="yes" url="" outputurl="" processortype="datadirect" tcpport="0" profilemode="0" profiledepth="" profilelength="" urlprofilexml="" commandline=""
		          additionalpath="" additionalclasspath="" postprocessortype="none" postprocesscommandline="" postprocessadditionalpath="" postprocessgeneratedext="" host="" port="0" user="" password="" validateoutput="no" validator="internal"
		          customvalidator="">
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