<#include "layout.ftl">

<#macro overrideTitle>
    仪表盘 -
</#macro>

<#macro overrideStyle>
<style>
    a.panel-body {
        display: block;
    }
</style>
</#macro>

<#macro overrideContent>
<div class="row mg-b">
    <div class="col-xs-6">
        <h3 class="no-margin">仪表盘</h3>
        <small>欢迎回来, 管理员</small>
    </div>
</div>
<div class="row">
    <div class="col-md-3 col-sm-6 col-xs-12">
        <section class="panel">
            <a class="panel-body" href="${contextPath}/admin/article.html">
                <div class="circle-icon bg-primary">
                    <i class="fa fa-book"></i>
                </div>
                <div>
                    <h3 class="no-margin">0</h3>
                    文章
                </div>
            </a>
        </section>
    </div>
    <div class="col-md-3 col-sm-6 col-xs-12">
        <section class="panel">
            <a class="panel-body" href="${contextPath}/admin/menu.html">
                <div class="circle-icon bg-success">
                    <i class="fa fa-list"></i>
                </div>
                <div>
                    <h3 class="no-margin">0</h3>
                    菜单
                </div>
            </a>
        </section>
    </div>
    <div class="col-md-3 col-sm-6 col-xs-12">
        <section class="panel">
            <a class="panel-body" href="${contextPath}/admin/category.html">
                <div class="circle-icon bg-danger">
                    <i class="fa fa-tasks"></i>
                </div>
                <div>
                    <h3 class="no-margin">0</h3>
                    分类
                </div>
            </a>
        </section>
    </div>
    <div class="col-md-3 col-sm-6 col-xs-12">
        <section class="panel">
            <a class="panel-body" href="${contextPath}/admin/module.html">
                <div class="circle-icon bg-info">
                    <i class="fa fa-object-group"></i>
                </div>
                <div>
                    <h3 class="no-margin">0</h3>
                    模块
                </div>
            </a>
        </section>
    </div>
    <div class="col-md-3 col-sm-6 col-xs-12">
        <section class="panel">
            <a class="panel-body" href="${contextPath}/admin/page.html">
                <div class="circle-icon bg-warning">
                    <i class="fa fa-file"></i>
                </div>
                <div>
                    <h3 class="no-margin">0</h3>
                    页面
                </div>
            </a>
        </section>
    </div>
</div>
<div class="row">

</div>
</#macro>

<#macro overrideScript>
<script type="text/javascript"
        src="${contextPath}/templates/default/admin/js/morris/morris.js"></script>
<script type="text/javascript"
        src="${contextPath}/templates/default/admin/js/dashboard.js"></script>
</#macro>

<@layout title=overrideTitle style=overrideStyle content=overrideContent script=overrideScript>
</@layout>