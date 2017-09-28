<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout" layout:decorate="~{layouts/layout}">
<#include "fragments/head.html">
<body>
<#include "fragments/navbar.html">
    <div class="container">
        <a href="${media.url}"><img src="${media.thumbnail}"></a>
    </div><!--/row-->
<#include "fragments/footer.html">
</body>
</html>
