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
            <a class="panel-body" href="${contextPath}/admin/biz.html">
                <div class="circle-icon bg-primary">
                    <i class="fa fa-codepen"></i>
                </div>
                <div>
                    <h3 class="no-margin">${bizs}</h3>
                    商家
                </div>
            </a>
        </section>
    </div>
    <div class="col-md-3 col-sm-6 col-xs-12">
        <section class="panel">
            <a class="panel-body" href="${contextPath}/admin/product.html">
                <div class="circle-icon bg-success">
                    <i class="fa fa-gift"></i>
                </div>
                <div>
                    <h3 class="no-margin">${products}</h3>
                    商品
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
                    <h3 class="no-margin">${categories}</h3>
                    分类
                </div>
            </a>
        </section>
    </div>
    <div class="col-md-3 col-sm-6 col-xs-12">
        <section class="panel">
            <a class="panel-body" href="${contextPath}/admin/brand.html">
                <div class="circle-icon bg-info">
                    <i class="fa fa-cube"></i>
                </div>
                <div>
                    <h3 class="no-margin">${brands}</h3>
                    品牌推广
                </div>
            </a>
        </section>
    </div>
    <div class="col-md-3 col-sm-6 col-xs-12">
        <section class="panel">
            <a class="panel-body" href="${contextPath}/admin/feedback.html">
                <div class="circle-icon bg-warning">
                    <i class="fa fa-commenting-o"></i>
                </div>
                <div>
                    <h3 class="no-margin">${feedbacks}</h3>
                    意见反馈
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