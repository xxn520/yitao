<#include "layout.ftl">

<#macro overrideTitle>
分类管理 -
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
    <div class="col-xs-6">
        <h3 class="no-margin">分类管理</h3>
    </div>
    <div class="col-xs-6 text-right">
        <a class="fa fa-plus btn btn-white" href="${contextPath}/admin/category/detail.html"> 添加分类</a>
    </div>
</div>
<div class="row">
    <div class="col-md-12">
        <section class="panel">
            <header class="panel-heading">列表</header>
            <div class="panel-heading">
                <form class="form-inline" role="form" method="get" action="${contextPath}/admin/category.html">
                    <div class="form-group">
                        <label for="categoryname">分类名: </label>
                        <input class="form-control" id="name" name="name" placeholder="分类名: 输入*模糊匹配" type="text" value="${request.getParameter("name")!}">
                    </div>
                    &nbsp;
                    <div class="form-group">
                        <label for="group_id">父分类: </label>
                        <select class="form-control" id="parent_id" name="parent_id" data-placeholder="父分类" style="width:120px">
                            <#if model.model??>
                                <option value="${model.model.id}" selected>${model.model.name}</option>
                            </#if>
                        </select>
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
                            <th>分类名</th>
                            <th>父分类</th>
                            <th>分类封面</th>
                            <th width="20%">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                            <#if (model.content?size)??&&(model.content?size>0)>
                                <#list model.content as model>
                                <tr>
                                    <td class="user-name form-inline">${model.name}</td>
                                    <td class="user-name form-inline"><a href="${contextPath}/admin/category.html?parent_id=${(model.parent.id)!}">${(model.parent.name)!}</a></td>
                                    <td><img src="${model.coverPhoto!}" class="thumbnail" style="max-height: 60px;max-width: 60px;"></td>
                                    <td nowrap="nowrap">
                                        <input type="hidden" name="id" class="category-id" value="${model.id}">
                                        <span class="action-normal">
                                            <#if (model.parent)??>
                                            <#else>
                                                <button class="fa fa-plus btn btn-white"> 添加子分类</button>
                                            </#if>
                                            <button class="fa fa-edit btn btn-white"> 编辑</button>
                                        </span>
                                    </td>
                                </tr>
                                </#list>
                            <#else>
                            <tr class="empty-row">
                                <td nowrap="nowrap">
                                    还没有分类呀，<a class="fa fa-plus btn btn-white" href="${contextPath}/admin/category/detail.html"> 添加</a> 一个
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
<script type="text/javascript" src="${contextPath}/templates/default/admin/js/select2.min.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        var $btn;
        $(document).ajaxError(function() {
                    toastr.error('出错了哟', undefined, {
                        positionClass: "toast-top-right"
                    });
                    $btn.button('reset');
                });
        $("select").select2({
            ajax: {
                url: "${contextPath}/admin/category/parent.json",
                cache: "true",
                data: function (params) {
                    return {
                        page: params.page
                    };
                },
                processResults: function (data) {
                    var options = [];
                    $(data.content).each(function () {
                        options.push({
                            id: this.id,
                            text: this.name
                        });
                    });
                    return {
                        results: options,
                        pagination: {
                            more: !data.last
                        }
                    }
                }
            },
            minimumResultsForSearch: Infinity
        });

        $(".fa-edit").click(function() {
            var id = $(this).parents("tr").find("input:hidden").val();
            location.href = "${contextPath}/admin/category/" + id + ".html";
        });

        $(".fa-plus").click(function() {
            var id = $(this).parents("tr").find("input:hidden").val();
            location.href = "${contextPath}/admin/category/detail.html?parent_id="+id;
        });

    });
</script>
</#macro>

<#macro overrideNav>
<ol class="breadcrumb no-margin">
    <li><a href="${contextPath}/admin/index.html">首页</a></li>
    <#if (model.model)??>
        <li><a href="${contextPath}/admin/category.html">分类</a></li>
        <li class="active">${model.model.name}</li>
    <#else>
        <li class="active">分类</li>
    </#if>
</ol>
</#macro>

<@layout title=overrideTitle style=overrideStyle content=overrideContent script=overrideScript nav=overrideNav>
</@layout>