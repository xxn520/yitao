<#include "layout.ftl">

<#macro overrideTitle>
	用户组管理 -
</#macro>

<#macro overrideStyle>
<link rel="stylesheet" href="${contextPath}/templates/default/admin/css/select2.min.css">
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
    <div class="col-xs-6">
        <h3 class="no-margin">用户组管理</h3>
    </div>
    <div class="col-xs-6 text-right">
        <a class="fa fa-plus btn btn-white" href="${contextPath}/admin/user-group/detail.html"> 添加用户组</a>
    </div>
</div>
<div class="row">
    <div class="col-md-12">
        <section class="panel">
            <header class="panel-heading">列表</header>
            <div class="panel-heading">
                <form class="form-inline" role="form" method="get" action="${contextPath}/admin/user-group.html">
                    <div class="form-group">
                        <label for="user-groupname">用户组名: </label>
                        <input class="form-control" id="name" name="name" placeholder="组名: 输入*模糊匹配" type="text" value="${request.getParameter("name")!}">
                    </div>
                    <button type="submit" class="btn btn-default">搜索</button>
                </form>
            </div>
            <div class="panel-body no-padding">
                <div class="table-responsive">
                    <table
                            class="table table-hover table-striped responsive" >
                        <thead>
                        <tr>
                            <th width="10%">用户组名</th>
                            <th>权限</th>
                            <th width="10%">操作</th>
                        </tr>
                        </thead>
                        <tbody>
						<#if (model.content?size>0)>
						<#list model.content as model>
                        <tr>
                            <td class="form-inline">${model.name}</td>
                            <td>
							<#list model.authorities as auth>
							    ${auth}
							</#list>
							</td>
                            <td nowrap="nowrap">
                                <input type="hidden" name="id" class="user-group-id" value="${model.id}">
                                <span class="action-normal">
                                    <button class="fa fa-edit btn btn-white"> 编辑</button>
                                    <#--<button class="fa fa-remove btn btn-danger" data-loading-text="处理中..." autocomplete="off"> 删除</button>-->
                                </span>
                            </td>
                        </tr>
						</#list>
                        <#else>
                        <tr class="empty-row">
                            <td nowrap="nowrap">
                                还没有用户组呀，<a class="fa fa-plus btn btn-white" href="${contextPath}/admin/user-group/detail.html"> 添加</a> 一个
                            </td>
                            <td></td>
                            <td></td>
                        </tr>
						</#if>
                        </tbody>
                    </table>
                </div>
            </div>
        </section>
        <div class="text-center">
            <ul class="pagination"></ul>
        </div>
    </div>
</div>
</#macro>

<#macro overrideScript>
<script type="text/javascript">
    $(document).ready(function() {
        var $btn;
        $(document).ajaxError(function() {
                    toastr.error('出错了哟', undefined, {
                        positionClass: "toast-top-center"
                    });
                    $btn.button('reset');
                })
                .on("click", "button.fa-remove", function() {
                    var tbody = $(this).parents("tbody");
                    var tr = $(this).parents("tr");
                    var idField = tr.find(".user-group-id");
                    if (idField.length) {
                        $btn = $(this).button('loading');
                        var url = "${contextPath}/admin/user-group/" + idField.val() + ".json?_method=DELETE";
                        var confirmed = confirm("你确定要删除吗？");
                        if (confirmed) {
                            $.post(url, function(data) {
                                $btn.button('reset');
                                if (data > 0) {
                                    tr.remove();
                                    if (tbody.find("tr").length == 2) {
                                        $(".empty-row").show();
                                    }
                                } else {
                                    toastr.error("无法删除该用户组。", undefined, {
                                        positionClass: "toast-top-center"
                                    });
                                }
                            });
                        } else {
                            $btn.button('reset');
                        }
                    } else {
                        $btn.button('reset');
                        tr.remove();
                        if (tbody.find("tr").length == 2) {
                            $(".empty-row").show();
                        }
                    }

                });

        $(".fa-edit").click(function() {
            var id = $(this).parents("tr").find("input:hidden").val();
            location.href = "${contextPath}/admin/user-group/" + id + ".html";
        });
    });
</script>
</#macro>

<#macro overrideNav>
<ol class="breadcrumb no-margin">
    <li><a href="${contextPath}/admin/index.html">首页</a></li>
    <#if (model.model)??>
        <li><a href="${contextPath}/admin/user-group.html">用户组</a></li>
        <li class="active">${model.model.name}</li>
	<#else>
        <li class="active">用户组</li>
    </#if>
</ol>
</#macro>

<@layout title=overrideTitle style=overrideStyle content=overrideContent script=overrideScript nav=overrideNav>
</@layout>