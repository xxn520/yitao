<#include "layout.ftl">

<#macro overrideTitle>
    <#if model??>
		编辑
	<#else>
		添加
	</#if>
	用户组
</#macro>

<#macro overrideStyle>
<link rel="stylesheet" href="${contextPath}/templates/default/admin/css/select2.min.css">
<link rel="stylesheet" href="${contextPath}/templates/default/admin/css/trumbowyg.min.css">
<link rel="stylesheet" href="${contextPath}/templates/default/admin/css/dropzone.min.css">
<style type="text/css">
    .touch .select2-results li:hover {
        background-color: #5897FB !important;
    }
    .trumbowyg-box {
        width: 100%;
        margin: 0;
        border-radius: 2px;
    }
    .trumbowyg-button-pane {
        background: none;
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
    .select2-dropdown, .select2-container--default .select2-search--dropdown .select2-search__field, .dropzone {
        border: 1px solid #E8ECF3;
        border-radius: 2px;
    }
</style>
</#macro>

<#macro overrideContent>
<div class="row mg-b">
    <div class="col-xs-12">
        <h3 class="no-margin">
			<#if model??>
                编辑
			<#else>
                添加
			</#if>用户组
		</h3>
    </div>
</div>
<div class="row">
    <div class="col-md-12">
        <section class="panel">
            <header class="panel-heading">填写用户组信息</header>
            <div class="panel-body">
                <form class="form-horizontal" action="../user-group<#if model??>/${model.id}</#if>.html<#if model??>?_method=PUT</#if>" method="post">
                    <#if model??>
                    <input type="hidden" name="id" value="${model.id}">
					</#if>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">用户组名</label>
                        <div class="col-sm-10">
                            <input class="form-control" name="name" type="text" placeholder="请输入组名" required="required" value="${(model.name)!}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">权限</label>
                        <div class="col-sm-10">
                            <select class="form-control" name="authorities" data-placeholder="请选择权限" multiple="multiple" style="width: 100%">
                                <#if (model.authorities)??>
									<#list model.authorities as auth>
                                        <option value="${auth}" selected="selected">${auth}</option>
									</#list>
                                </#if>
                            </select>
                        </div>
                    </div>
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

<#macro overrideScript>
<script type="text/javascript" src="${contextPath}/templates/default/admin/js/select2.min.js"></script>
<script type="text/javascript" src="${contextPath}/templates/default/admin/js/dropzone.min.js"></script>
<script type="text/javascript" src="${contextPath}/templates/default/admin/js/switchery/switchery.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $(".btn-reset").click(function() {
            location.href = "${contextPath}/admin/user-group.html";
        });

        $("select").select2({
            ajax: {
                url: "${contextPath}/admin/user-group/authorities.json",
                cache: "true",
                processResults: function(data) {
                    var options = [];
                    $(data).each(function() {
                        options.push({
                            id: this,
                            text: this
                        });
                    });
                    return {
                        results: options
                    }
                }
            },
            tags: true,
            minimumResultsForSearch: Infinity
        });

        //模块选择原有顺序移除
        $("select").on("select2:unselect", function (evt) {
            var element = evt.params.data.element;
            $(element).remove();
        });

    });
</script>
</#macro>

<#macro overrideNav>
<ol class="breadcrumb no-margin">
    <li><a href="${contextPath}/admin/index.html">首页</a></li>
    <li><a href="${contextPath}/admin/user-group.html">用户组</a></li>
    <li class="active">
		<#if model??>
			编辑
		<#else>
			添加
		</#if>用户组
	</li>
</ol>
</#macro>

<@layout title=overrideTitle style=overrideStyle content=overrideContent script=overrideScript nav=overrideNav>
</@layout>