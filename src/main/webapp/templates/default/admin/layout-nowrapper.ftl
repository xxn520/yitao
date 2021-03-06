<#macro defaultStyle>
</#macro>

<#macro defaultContent>
</#macro>

<#macro defaultScript>
</#macro>

<#macro defaultTitle>
</#macro>

<#macro layoutNowrapper title=defaultTitle style=defaultStyle content=defaultContent script=defaultFooter>
    <#assign contextPath="http://localhost:8080" />
<!doctype html>
<html class="no-js" lang="">
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1, maximum-scale=1">
    <title><@title />${app.name}</title>

    <link rel="stylesheet"
          href="${contextPath}/templates/default/admin/css/pace/theme.css">
    <link rel="stylesheet"
          href="${contextPath}/templates/default/admin/css/jvectormap/jquery-jvectormap-1.2.2.css">
    <link rel="stylesheet"
          href="${contextPath}/templates/default/admin/css/bxslider/jquery.bxslider.css">
    <link rel="stylesheet"
          href="${contextPath}/templates/default/admin/css/toastr.min.css">


    <link rel="stylesheet"
          href="${contextPath}/templates/default/admin/css/bootstrap/bootstrap.min.css">
    <link rel="stylesheet"
          href="${contextPath}/templates/default/admin/css/font-awesome.min.css">
    <link rel="stylesheet"
          href="${contextPath}/templates/default/admin/css/animate.min.css">

    <link rel="stylesheet"
          href="${contextPath}/templates/default/admin/css/panel.css">

    <link rel="stylesheet"
          href="${contextPath}/templates/default/admin/css/skins/palette.1.css"
          id="skin">
    <link rel="stylesheet"
          href="${contextPath}/templates/default/admin/css/main.css">
    <@style />

    <!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <script src="${contextPath}/templates/default/admin/js/modernizr.js"></script>
</head>

<body class="bg-color center-wrapper">
<div class="center-content">
    <@content />
</div>
</body>

</html>

<script type="text/javascript"
        src="${contextPath}/templates/default/admin/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript"
        src="${contextPath}/templates/default/admin/js/bootstrap/bootstrap.js"></script>
<script type="text/javascript"
        src="${contextPath}/templates/default/admin/js/jquery.easing.min.js"></script>
<script type="text/javascript"
        src="${contextPath}/templates/default/admin/js/jquery.placeholder.js"></script>
<script type="text/javascript"
        src="${contextPath}/templates/default/admin/js/fastclick.js"></script>
<script type="text/javascript"
        src="${contextPath}/templates/default/admin/js/moment.js"></script>
<script type="text/javascript"
        src="${contextPath}/templates/default/admin/js/skycons.js"></script>
<script type="text/javascript"
        src="${contextPath}/templates/default/admin/js/jquery.blockUI.js"></script>
<script type="text/javascript"
        src="${contextPath}/templates/default/admin/js/raphael.min.js"></script>
<script type="text/javascript"
        src="${contextPath}/templates/default/admin/js/switchery/switchery.js"></script>
<script type="text/javascript"
        src="${contextPath}/templates/default/admin/js/jquery.slimscroll.js"></script>
<script type="text/javascript"
        src="${contextPath}/templates/default/admin/js/bxslider/jquery.bxslider.min.js"></script>
<script type="text/javascript"
        src="${contextPath}/templates/default/admin/js/pace/pace.min.js"></script>
<script type="text/javascript"
        src="${contextPath}/templates/default/admin/js/off-canvas.js"></script>
<script type="text/javascript"
        src="${contextPath}/templates/default/admin/js/main.js"></script>
<script type="text/javascript"
        src="${contextPath}/templates/default/admin/js/panel.js"></script>
<script type="text/javascript"
        src="${contextPath}/templates/default/admin/js/toastr.min.js"></script>
<#if model??>
<script type="text/javascript" src="${contextPath}/templates/default/admin/js/jquery.twbsPagination.min.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $('.pagination').twbsPagination({
            totalPages : ${model.totalPages},
        startPage: ${model.number} + 1,
                initiateStartPageClick: false,
                first: "«",
                prev: "‹",
                next: "›",
                last: "»",
                onPageClick : function(event, page) {
            var href = location.search;
            href = href.indexOf("page=") > 0 ? href.replace(/page=[\d]+/, "page=" + (page - 1)) : href + "&page=" + (page - 1);
            location.href = href;
        }
    });
    });
</script>
</#if>
<@script/>
</#macro>

