<#include "layout.ftl">

<#macro overrideTitle>
    <#if model??>
    编辑意见反馈 -
    <#else>
    添加意见反馈 -
    </#if>
</#macro>

<#macro overrideStyle>
<link rel="stylesheet" href="${contextPath}/templates/default/admin/css/select2.min.css">
<link rel="stylesheet" href="${contextPath}/templates/default/admin/css/trumbowyg.min.css">
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
    .select2-dropdown, .select2-container--default .select2-search--dropdown .select2-search__field {
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
                编辑意见反馈
            <#else>
                添加意见反馈
            </#if>
        </h3>
    </div>
</div>
<div class="row">
    <div class="col-md-12">
        <section class="panel">
            <header class="panel-heading">填写意见反馈信息</header>
            <div class="panel-body">
                <form class="form-horizontal" action="../feedback<#if model??>/${model.id!}</#if>.html<#if model??>?_method=PUT</#if>" method="post">
                    <#if model??>
                        <input type="hidden" name="id" value="${(model.id)!}">
                    </#if>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">反馈内容</label>
                        <div class="col-sm-10">
                            <textarea class="form-control" name="detail" id="detail"  placeholder="请输入反馈内容" rows="10">${(model.detail)!}</textarea>
                            <label class="error-information"></label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">联系方式</label>
                        <div class="col-sm-10">
                            <input class="form-control" name="contact" type="text" placeholder="请输入联系方式" value="${(model.contact)!}">
                            <label class="error-information"></label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">是否处理过</label>
                        <div class="col-sm-10">
                            <input class="js-switch-green" type="checkbox" name="handled" value="true"<#if (model.handled)??&&(model.handled)> checked</#if>/>
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
            location.href = "${contextPath}/admin/feedback.html";
        });

        $(".js-switch-green").each(function() {
            new Switchery(this, {
                color : '#2dcb73'
            });
        });
        //表单验证
        var detail = $("#detail");
        var contact = $("input[name='contact']");
        var validateFunc = function(dom){
            var validator = new Validator();
            validator.add(detail,[
                {strategy:'isNotEmpty',errorMsg:'内容不能为空!'},
            ]);
            validator.add(contact,[
                {strategy:'isNotEmpty',errorMsg:'联系方式不能为空!'},
            ]);
            var error= validator.start(dom);
            return error;
        };

        $('input').blur(function(){
            validateFunc(this);
        });

        $('#detail').blur(function(){
            validateFunc(this);
        });

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
    <li><a href="${contextPath}/admin/feedback.html">意见反馈</a></li>
    <li class="active">
        <#if model??>
            编辑意见反馈
        <#else>
            添加意见反馈
        </#if>
    </li>
</ol>
</#macro>

<@layout title=overrideTitle style=overrideStyle content=overrideContent script=overrideScript nav=overrideNav>
</@layout>