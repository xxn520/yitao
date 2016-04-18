<#include "layout.ftl">

<#macro overrideTitle>
    编辑折扣 -
</#macro>

<#macro overrideStyle>
<link rel="stylesheet" href="${contextPath}/templates/default/admin/css/trumbowyg.min.css">
<link rel="stylesheet" href="${contextPath}/templates/default/admin/css/bootstrap-datepicker3.min.css">
<style type="text/css">
    .trumbowyg-box {
        width: 100%;
        margin: 0;
        border-radius: 2px;
    }
    .trumbowyg-button-pane {
        background: none;
    }
</style>
</#macro>

<#macro overrideContent>
<div class="row mg-b">
    <div class="col-xs-12">
        <h3 class="no-margin">
            编辑折扣
        </h3>
    </div>
</div>
<div class="row">
    <div class="col-md-12">
        <section class="panel">
            <ul class="nav nav-tabs">
                <li><a href="${contextPath}/admin/product/${request.getParameter("product_id")!}.html">商品信息</a></li>
                <li class="active"><a href="javascript:void(0)">折扣信息</a></li>
            </ul>
            <div class="panel-body">
                <form class="form-horizontal" action="../discount/${model.id!}.html?_method=PUT&product_id=${request.getParameter("product_id")!}" method="post">
                    <#if model??>
                        <input type="hidden" name="id" value="${(model.id)!}">
                    </#if>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">折扣价</label>
                        <div class="col-sm-10">
                            <input class="form-control" name="discount" id="discount" type="text" placeholder="请输入折扣价"  value="${(model.discount)!}">
                            <label class="error-information"></label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">介绍</label>
                        <div class="col-sm-10">
                            <textarea class="form-control" name="introduction" id="introduction" type="text" placeholder="请输入介绍" rows="10">${(model.introduction)!}</textarea>
                            <label class="error-information"></label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">介绍</label>
                        <div class="col-sm-10">
                        <div class="form-control input-group input-daterange">
                            <input class="form-control" id="startDate" name="startDate" placeholder="请选择起始时间" type="text" value="${(model.startDate?string('yyyy年MM月dd日'))!}">
                            <span class="input-group-addon"> to </span>
                            <input class="form-control" id="endDate" name="endDate" placeholder="请选择结束时间" type="text" value="${(model.endDate?string('yyyy年MM月dd日'))!}">
                        </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">是否过期</label>
                        <div class="col-sm-10">
                            <input class="js-switch-green" type="checkbox" name="flag" value="true"<#if (model.flag)??&&(model.flag)> checked</#if>/>
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
<script type="text/javascript" src="${contextPath}/templates/default/admin/js/switchery/switchery.js"></script>
<script type="text/javascript" src="${contextPath}/templates/default/admin/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="${contextPath}/templates/default/admin/js/bootstrap-datepicker.zh-CN.min.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $(".btn-reset").click(function() {
            location.href = "${contextPath}/admin/product.html";
        });

        $(".js-switch-green").each(function() {
            new Switchery(this, {
                color : '#2dcb73'
            });
        });

        $('.input-daterange').datepicker({
            language: 'zh-CN'
        });

        //表单验证
        var introduction = $("textarea[name='introduction']");
        var discount = $("input[name='discount']");
        var validateFunc = function(dom){
            var validator = new Validator();
            validator.add(introduction,[
                {strategy:'isNotEmpty',errorMsg:'介绍不能为空!'},
            ]);
            validator.add(discount,[
                {strategy:'isPrice',errorMsg:'价格必须是正整数,最多两位小数!'}
            ]);
            var error= validator.start(dom);
            return error;
        };

        $('input').blur(function(){
            validateFunc(this);
        })

        $("textarea[name='introduction']").blur(function(){
            validateFunc(this);
        })

        $("form").submit(function(e) {
            var error = validateFunc();
            if(error){
                return true;
            }
            e.preventDefault();
            return false;
        });
    });
</script>
</#macro>

<#macro overrideNav>
<ol class="breadcrumb no-margin">
    <li><a href="${contextPath}/admin/index.html">首页</a></li>
    <li><a href="${contextPath}/admin/discount.html">折扣</a></li>
    <li class="active">
        编辑折扣
    </li>
</ol>
</#macro>

<@layout title=overrideTitle style=overrideStyle content=overrideContent script=overrideScript nav=overrideNav>
</@layout>