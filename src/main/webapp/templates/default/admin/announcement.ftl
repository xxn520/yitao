<#include "layout.ftl">

<#macro overrideTitle>
公告管理 -
</#macro>

<#macro overrideContent>
<div class="row mg-b">
    <div class="col-xs-6">
        <h3 class="no-margin">公告管理</h3>
    </div>
    <div class="col-xs-6 text-right">
        <a class="fa fa-plus btn btn-white" href="${contextPath}/admin/announcement/detail.html"> 添加公告</a>
    </div>
</div>
<div class="row">
    <div class="col-md-12">
        <section class="panel">
            <header class="panel-heading">列表</header>
            <div class="panel-heading">
                <form class="form-inline" role="form" method="get" action="${contextPath}/admin/announcement.html">
                    <div class="form-group">
                        <label for="content">标题: </label>
                        <input class="form-control" id="title" name="title" placeholder="标题: 输入*模糊匹配" type="text" value="${request.getParameter("title")!}">
                    </div>
                    &nbsp;
                    <div class="form-group">
                        <label for="detail">内容: </label>
                        <input class="form-control" id="detail" name="detail" placeholder="内容: 输入*模糊匹配" type="text" value="${request.getParameter("detail")!}">
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
                            <th>发布者</th>
                            <th>标题</th>
                            <th>内容</th>
                            <th>联系方式</th>
                            <th width="20%">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                            <#if (model.content?size)??&&(model.content?size>0)>
                                <#list model.content as model>
                                <tr>
                                    <td class="user-name form-inline"><a href="${contextPath}/admin/user.html?createdBy_username=${model.createdBy.username!}">${model.createdBy.username!}</a></td>
                                    <td>${model.title!}</td>
                                    <td>${model.detail!}</td>
                                    <td>${model.contact!}</td>
                                    <td nowrap="nowrap">
                                        <input type="hidden" name="id" class="announcement-id" value="${model.id}">
                                        <span class="action-normal">
                                            <button class="fa fa-edit btn btn-white"> 编辑</button>
                                            <button class="fa fa-remove btn btn-danger" data-loading-text="处理中..." autocomplete="off"> 删除</button>
                                        </span>
                                    </td>
                                </tr>
                                </#list>
                            <#else>
                            <tr class="empty-row">
                                <td nowrap="nowrap">
                                    还没有反馈呀，<a class="fa fa-plus btn btn-white" href="${contextPath}/admin/announcement/detail.html"> 添加</a> 一个
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
                })
                .on("click", "button.fa-remove", function() {
                    var tbody = $(this).parents("tbody");
                    var tr = $(this).parents("tr");
                    var idField = tr.find(".announcement-id");
                    if (idField.length) {
                        $btn = $(this).button('loading');
                        var url = "${contextPath}/admin/announcement/" + idField.val() + ".json?_method=DELETE";
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
                                    toastr.error("无法删除该意见反馈。", undefined, {
                                        positionClass: "toast-top-right"
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
            location.href = "${contextPath}/admin/announcement/" + id + ".html";
        });

    });
</script>
</#macro>

<#macro overrideNav>
<ol class="breadcrumb no-margin">
    <li><a href="${contextPath}/admin/index.html">首页</a></li>
    <li class="active">公告</li>
</ol>
</#macro>

<@layout title=overrideTitle style=defaultStyle content=overrideContent script=overrideScript nav=overrideNav>
</@layout>