<users>
{
for $e in doc('AuditTrail.xml')/audit_trail/event
where $e/type = \"submitForBatch\" and not ($e[processed])
return doc($e/metadata/file_name/text())/users/*
}
</users>
