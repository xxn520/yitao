<#include "layout.ftl">

<#macro overrideTitle>
商品管理 -
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
        <h3 class="no-margin">商品管理</h3>
    </div>
    <div class="col-xs-6 text-right">
        <a class="fa fa-plus btn btn-white" href="${contextPath}/admin/product/detail.html"> 添加商品</a>
    </div>
</div>
<div class="row">
    <div class="col-md-12">
        <section class="panel">
            <header class="panel-heading">列表</header>
            <div class="panel-heading">
                <form class="form-inline" role="form" method="get" action="${contextPath}/admin/product.html">
                    <div class="form-group">
                        <label for="name">商品名: </label>
                        <input class="form-control" id="name" name="name" placeholder="商品名: 输入*模糊匹配" type="text" value="${request.getParameter("name")!}">
                    </div>
                    &nbsp;
                    <div class="form-group">
                        <label for="biz_id">商家: </label>
                        <select class="form-control" id="biz_id" name="biz_id" data-placeholder="商家" style="width:120px">
                            <#if (model.model)??>
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
                            <th>商家名</th>
                            <th>商品名</th>
                            <th>商品介绍</th>
                            <th>封面图</th>
                            <th>原价</th>
                            <th width="25%">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                            <#if (model.content?size)??&&(model.content?size>0)>
                                <#list model.content as model>
                                <tr>
                                    <td class="user-name form-inline">${(model.biz.name)!}</td>
                                    <td class="user-name form-inline">${model.name!}</td>
                                    <td>${model.introduce!}</td>
                                    <td><img src="${model.coverPhoto!}" class="thumbnail" style="max-height: 60px;max-width: 60px;"></td>
                                    <td>${model.price!}</td>
                                    <td nowrap="nowrap">
                                        <input type="hidden" name="id" class="product-id" value="${model.id}">
                                        <input type="hidden" name="discount-id" class="discount-id" value="${model.discount.id}">
                                        <span class="action-normal">
                                            <button class="fa fa-edit btn btn-white"> 编辑</button>
                                            <button class="fa fa-file-image-o btn btn-white"> 编辑详情图</button>
                                            <button class="fa fa-remove btn btn-danger" data-loading-text="处理中..." autocomplete="off"> 删除</button>
                                        </span>
                                    </td>
                                </tr>
                                </#list>
                            <#else>
                            <tr class="empty-row">
                                <td nowrap="nowrap">
                                    还没有商品呀，<a class="fa fa-plus btn btn-white" href="${contextPath}/admin/product/detail.html"> 添加</a> 一个
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
                })
                .on("click", "button.fa-remove", function() {
                    var tbody = $(this).parents("tbody");
                    var tr = $(this).parents("tr");
                    var idField = tr.find(".product-id");
                    if (idField.length) {
                        $btn = $(this).button('loading');
                        var url = "${contextPath}/admin/product/" + idField.val() + ".json?_method=DELETE";
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
                                    toastr.error("无法删除该商品。", undefined, {
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

        $("select").select2({
            ajax: {
                url: "${contextPath}/admin/biz.json",
                cache: "true",
                data: function (params) {
                    return {
                        page: params.page
                    };
                },
                processResults: function(data) {
                    var options = [{
                        id: 0,
                        text: "全部"
                    }];
                    $(data.content).each(function() {
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
            location.href = "${contextPath}/admin/product/" + id + ".html";
        });

        $(".fa-level-down").click(function() {
            location.href = "${contextPath}/admin/product-detailPhoto.html";
        });

    });
</script>
</#macro>

<#macro overrideNav>
<ol class="breadcrumb no-margin">
    <li><a href="${contextPath}/admin/index.html">首页</a></li>
    <#if (model.model)??>
        <li><a href="${contextPath}/admin/product.html">商品</a></li>
        <li class="active">${model.model.name}</li>
    <#else>
        <li class="active">商品</li>
    </#if>
</ol>
</#macro>

<@layout title=overrideTitle style=overrideStyle content=overrideContent script=overrideScript nav=overrideNav>
</@layout>