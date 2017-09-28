<!DOCTYPE html>
<html lang="en">
<#include "fragments/head.html">
<body>
<script>

</script>

<#list entries as yams>
    <br>${yams.userid}
</#list>
<form action="/admin/upload" enctype="multipart/form-data" method="POST">
    <input type="file" name="file">
    <input type="submit" value="submit">
</form>

</body>
</html>