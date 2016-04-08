<#include "layout.ftl">

<#macro overrideTitle>
	设置 -
</#macro>

<#macro overrideStyle>
<link rel="stylesheet" href="${contextPath}/templates/default/admin/css/select2.min.css">
<style type="text/css">
    .touch .select2-results li:hover {
        background-color: #5897FB !important;
    }
    .select2-container--default .select2-selection--single {
        border-radius: 2px;
        border: 1px solid #E8ECF3;
        line-height: 34px;
        height: 34px;
    }
    .select2-container--default .select2-selection--single .select2-selection__rendered {
        line-height: 34px;
    }
    .select2-dropdown, .select2-container--default .select2-search--dropdown .select2-search__field {
        border: 1px solid #E8ECF3;
        border-radius: 2px;
    }
</style>
</#macro>

<#macro overrideContent>
<div class="row mg-b">
    <div class="col-xs-12">
        <h3 class="no-margin">系统设置</h3>
    </div>
</div>
<div class="row">
    <div class="col-md-12">
        <section class="panel">
            <header class="panel-heading">填写分类信息</header>
            <div class="panel-body">
                <form class="form-horizontal" action="${contextPath}/admin/setting.html" method="post">
                    <#if (model?size>0)>
                    <#list model as model>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">${model.name}</label>
                        <div class="col-sm-10">
                            <input class="form-control" name="${model.name}" type="text" value="${model.value}">
                        </div>
                    </div>
                    </#list>
                    </#if>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-success">提交</button>
                            <button type="reset" class="btn btn-reset btn-default">取消</button>
                        </div>
                    </div>
                </form>
            </div>
        </section>
    </div>
</div>
</#macro>

<#macro overrideNav>
<ol class="breadcrumb no-margin">
    <li><a href="${contextPath}/admin/index.html">首页</a></li>
    <li class="active">设置</li>
</ol>
</#macro>

<@layout title=overrideTitle style=overrideStyle content=overrideContent script=defaultScript nav=overrideNav>
</@layout>