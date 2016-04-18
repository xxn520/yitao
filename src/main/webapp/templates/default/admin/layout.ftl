<#macro defaultTitle>
</#macro>

<#macro defaultStyle>
</#macro>

<#macro defaultContent>
</#macro>

<#macro defaultScript>
</#macro>

<#macro defaultNav>
</#macro>

<#macro loc path location>
	<#if path==location>
		active
	</#if>
</#macro>

<#macro layout title=defaultTitle style=defaultStyle content=defaultContent script=defaultScript nav=defaultNav loc=loc>
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

	<body>
		<div class="app" data-sidebar="locked">
			<header class="header header-fixed navbar">
				<div class="brand">
					<a href="javascript:;" class="fa fa-bars off-left visible-xs"
						data-toggle="off-canvas" data-move="ltr"></a> <a href="index.html"
						class="navbar-brand"> <i class="fa fa-stop mg-r-sm"></i> <span
						class="heading-font"> ${app.name} <b>控制台</b>
					</span>
					</a>
				</div>
				<div class="navbar-left navbar-form hidden-xs">
					<@nav />
				</div>
				<ul class="nav navbar-nav navbar-right off-right">
					<li class="hidden-xs"><a href="javascript:;"> ${currentUser.username} </a></li>
					<li class="quickmenu">
						<a href="javascript:;" data-toggle="dropdown">
							<img src="${currentUser.avatar?default("${contextPath}/templates/default/admin/img/avatar.jpg")}"
								class="avatar pull-left img-circle" alt="user" title="user">
								<i class="caret mg-l-xs hidden-xs no-margin"></i>
						</a>
						<ul class="dropdown-menu dropdown-menu-right mg-r-xs">
							<li><a href="${contextPath}/admin/profile.html">账号设置</a></li>
							<li><a href="${contextPath}/admin/reset.html">重设密码</a></li>
							<li class="divider"></li>
							<li><a href="${contextPath}/admin/logout.do">退出</a></li>
						</ul></li>
				</ul>
			</header>

			<section class="layout">

				<aside class="sidebar canvas-left">

					<nav class="main-navigation">
						<ul>
							<li class="<@loc "/admin/index.html" "${location}"/>">
								<a href="${contextPath}/admin/index.html">
									<i class="fa fa-home"></i>
									<span>首页</span>
								</a>
							</li>
                            <li class="<@loc "/admin/biz.html" "${location}"/>">
                                <a href="${contextPath}/admin/biz.html">
                                    <i class="fa fa-codepen"></i>
                                    <span>商家管理</span>
                                </a>
                            </li>
                            <li class="<@loc "/admin/product.html" "${location}"/>">
                                <a href="${contextPath}/admin/product.html">
                                    <i class="fa fa-gift"></i>
                                    <span>商品管理</span>
                                </a>
                            </li>
                            <li class="<@loc "/admin/category.html" "${location}"/>">
                                <a href="${contextPath}/admin/category.html">
                                    <i class="fa fa-tasks"></i>
                                    <span>分类管理</span>
                                </a>
                            </li>
                            <li class="<@loc "/admin/brand.html" "${location}"/>">
                                <a href="${contextPath}/admin/brand.html">
                                    <i class="fa fa-cube"></i>
                                    <span>品牌推广</span>
                                </a>
                            </li>
                            <li class="<@loc "/admin/message.html" "${location}"/>">
                                <a href="${contextPath}/admin/message.html">
                                    <i class="fa  fa-comments"></i>
                                    <span>消息管理</span>
                                </a>
                            </li>
                            <li class="<@loc "/admin/announcement.html" "${location}"/>">
                                <a href="${contextPath}/admin/announcement.html">
                                    <i class="fa fa-rss"></i>
                                    <span>公告管理</span>
                                </a>
                            </li>
                            <li class="<@loc "/admin/feedback.html" "${location}"/>">
                                <a href="${contextPath}/admin/feedback.html">
                                    <i class="fa fa-commenting-o"></i>
                                    <span>意见反馈</span>
                                </a>
                            </li>
							<li class="<@loc "/admin/user.html" "${location}"/>">
								<a href="${contextPath}/admin/user.html">
									<i class="fa fa-user"></i>
									<span>用户</span>
								</a>
							</li>
							<li class="<@loc "/admin/user-group.html" "${location}"/>">
								<a href="${contextPath}/admin/user-group.html"> <i class="fa fa-users"></i> <span>用户组</span>
							</a></li>
							<li class="<@loc "/admin/setting.html" "${location}"/>">
								<a href="${contextPath}/admin/setting.html">
									<i class="fa fa-gears"></i>
									<span>设置</span>
								</a>
							</li>
						</ul>
					</nav>

					<footer>
						<div class="footer-toolbar pull-left">
							 <a href="javascript:;"
								class="toggle-sidebar pull-right hidden-xs"> <i
								class="fa fa-angle-left"></i>
							</a>
						</div>
					</footer>

				</aside>

				<section class="main-content">

					<div class="content-wrap"><@content /></div>

				</section>

			</section>
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
		src="${contextPath}/templates/default/admin/js/validationcheck.js"></script>
	<script type="text/javascript"
		src="${contextPath}/templates/default/admin/js/toastr.min.js"></script>
	<#if (model.totalPages)??>
    <script type="text/javascript" src="${contextPath}/templates/default/admin/js/jquery.twbsPagination.min.js"></script>
    <script>
		$('.pagination').twbsPagination({
				totalPages : ${model.totalPages!},
				startPage: ${model.number!} + 1,
				initiateStartPageClick: false,
				first: "«",
				prev: "‹",
				next: "›",
				last: "»",
			onPageClick : function(event, page) {
				var href = location.search;
				if(href!=""){
					href = href.indexOf("page=") > 0 ? href.replace(/page=[\d]+/, "page=" + (page - 1)) : href + "&page=" + (page - 1);
				}else{
					href = location.href + "?page=" + (page - 1);
				}
				location.href = href;
			}
		});
    </script>
	</#if>
	<@script />
</#macro>