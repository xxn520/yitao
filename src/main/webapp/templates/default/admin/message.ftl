<#include "layout.ftl">

<#macro overrideTitle>
消息管理 -
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
        <h3 class="no-margin">消息管理</h3>
    </div>
    <div class="col-xs-6 text-right">
        <a class="fa fa-plus btn btn-white" href="${contextPath}/admin/message/detail.html"> 添加消息</a>
    </div>
</div>
<div class="row">
    <div class="col-md-12">
        <section class="panel">
            <header class="panel-heading">列表</header>
            <div class="panel-heading">
                <form class="form-inline" role="form" method="get" action="${contextPath}/admin/message.html">
                    <div class="form-group">
                        <label for="content">标题: </label>
                        <input class="form-control" id="title" name="title" placeholder="标题: 输入*模糊匹配" type="text" value="${request.getParameter("title")!}">
                    </div>
                    &nbsp;
                    <div class="form-group">
                        <label for="detail">内容: </label>
                        <input class="form-control" id="detail" name="detail" placeholder="内容: 输入*模糊匹配" type="text" value="${request.getParameter("detail")!}">
                    </div>
                    &nbsp;
                    <div class="form-group">
                        <label for="msgtype">消息类型: </label>
                        <select class="form-control" id="msgtype" name="msgtype" data-placeholder="消息类型" style="width:90px">
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
                            <th>发送者</th>
                            <th>接收者</th>
                            <th>标题</th>
                            <th>内容</th>
                            <th>消息类型</th>
                            <th width="20%">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                            <#if (model.content?size)??&&(model.content?size>0)>
                                <#list model.content as model>
                                <tr>
                                    <td class="user-name form-inline"><a href="${contextPath}/admin/user.html?createdBy_username=${model.createdBy.username!}">${model.createdBy.username!}</a></td>
                                    <td class="user-name form-inline"><a href="${contextPath}/admin/user.html?msgto_username=${model.msgto.username!}">${model.msgto.username}</a></td>
                                    <td>${model.title!}</td>
                                    <td>${model.detail!}</td>
                                    <td>
                                        <#if model.msgtype=="SYSTEM_MSG">
                                            系统消息
                                        <#elseif model.msgtype="PRIVATE_MSG">
                                            私信
                                        <#else>
                                            折扣消息
                                        </#if>
                                    </td>
                                    <td nowrap="nowrap">
                                        <input type="hidden" name="id" class="message-id" value="${model.id}">
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
                                    还没有反馈呀，<a class="fa fa-plus btn btn-white" href="${contextPath}/admin/message/detail.html"> 添加</a> 一个
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
<script type="text/javascript" src="${contextPath}/templates/default/admin/js/jquery.twbsPagination.min.js"></script>
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
                    var idField = tr.find(".message-id");
                    if (idField.length) {
                        $btn = $(this).button('loading');
                        var url = "${contextPath}/admin/message/" + idField.val() + ".json?_method=DELETE";
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

        var selectData = [{ id:'ALL',text:'全部'}, { id: 'SYSTEM_MSG',text: '系统消息'}, {  id: 'PRIVATE_MSG',text:'私信'}, {  id:'COLLECT_PRODUCT_MSG',text:'折扣消息' }];

        $("select#msgtype").select2({
            data: selectData,
            minimumResultsForSearch: Infinity
        });

        $('form').submit(function(){
            $('select option[value="ALL"]').attr('value', "");
            return true;
        });

        $(".fa-edit").click(function() {
            var id = $(this).parents("tr").find("input:hidden").val();
            location.href = "${contextPath}/admin/message/" + id + ".html";
        });

        var searchSelect=function(){
            var url_search=location.search;
            if(url_search.indexOf("msgtype=")>0){
                var msgtype=url_search.match(/msgtype=\w+/);
                if(msgtype){
                    msgtype=msgtype[0].split("=")[1];
                    var obj=$("select#msgtype option[value='"+msgtype+"']");
                    if(obj.length){
                        text=obj.attr("selected","selected").text();
                        $("#select2-msgtype-container").text(text);
                    }
                }
            }
        }

        searchSelect();
    });
</script>
</#macro>

<#macro overrideNav>
<ol class="breadcrumb no-margin">
    <li><a href="${contextPath}/admin/index.html">首页</a></li>
    <li class="active">消息</li>
</ol>
</#macro>

<@layout title=overrideTitle style=overrideStyle content=overrideContent script=overrideScript nav=overrideNav>
</@layout>