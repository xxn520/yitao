<#include "layout.ftl">

<#macro overrideTitle>
商家管理 -
</#macro>

<#macro overrideContent>
<div class="row mg-b">
    <div class="col-xs-6">
        <h3 class="no-margin">商家管理</h3>
    </div>
    <div class="col-xs-6 text-right">
        <a class="fa fa-plus btn btn-white" href="${contextPath}/admin/biz/detail.html"> 添加商家</a>
    </div>
</div>
<div class="row">
    <div class="col-md-12">
        <section class="panel">
            <header class="panel-heading">列表</header>
            <div class="panel-heading">
                <form class="form-inline" role="form" method="get" action="${contextPath}/admin/biz.html">
                    <div class="form-group">
                        <label for="bizname">商家名: </label>
                        <input class="form-control" id="name" name="name" placeholder="商家名: 输入*模糊匹配" type="text" value="${request.getParameter("name")!}">
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
                            <th>商家名</th>
                            <th>封面图</th>
                            <th>联系电话</th>
                            <th>联系地址</th>
                            <th width="20%">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                            <#if (model.content?size)??&&(model.content?size>0)>
                                <#list model.content as model>
                                <tr>
                                    <td class="user-name form-inline">${model.name}</td>
                                    <td><img src="${model.coverPhoto!}" class="thumbnail" style="max-height: 60px;max-width: 60px;"></td>
                                    <td>${model.phone!}</td>
                                    <td>${model.address!}</td>
                                    <td nowrap="nowrap">
                                        <input type="hidden" name="id" class="biz-id" value="${model.id}">
                                        <span class="action-normal">
                                            <button class="fa fa-edit btn btn-white"> 编辑</button>
                                            <button class="fa fa-list btn btn-white"> 查看商品</button>
                                        </span>
                                    </td>
                                </tr>
                                </#list>
                            <#else>
                            <tr class="empty-row">
                                <td nowrap="nowrap">
                                    还没有商家呀，<a class="fa fa-plus btn btn-white" href="${contextPath}/admin/biz/detail.html"> 添加</a> 一个
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
                        positionClass: "toast-top-right"
                    });
                    $btn.button('reset');
                });

        $(".fa-edit").click(function() {
            var id = $(this).parents("tr").find("input:hidden").val();
            location.href = "${contextPath}/admin/biz/" + id + ".html";
        });

        $(".fa-list").click(function() {
            var id = $(this).parents("tr").find("input:hidden").val();
            location.href = "${contextPath}/admin/product?biz_id=" + id + ".html";
        });

    });
</script>
</#macro>

<#macro overrideNav>
<ol class="breadcrumb no-margin">
    <li><a href="${contextPath}/admin/index.html">首页</a></li>
    <#if (model.model)??>
        <li><a href="${contextPath}/admin/biz.html">商家</a></li>
        <li class="active">${model.model.name}</li>
    <#else>
        <li class="active">商家</li>
    </#if>
</ol>
</#macro>

<@layout title=overrideTitle style=defaultStyle content=overrideContent script=overrideScript nav=overrideNav>
</@layout>