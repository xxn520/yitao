<#include "layout.ftl">

<#macro overrideTitle>
    <#if model??>
    编辑公告 -
    <#else>
    添加公告 -
    </#if>
</#macro>

<#macro overrideContent>
<div class="row mg-b">
    <div class="col-xs-12">
        <h3 class="no-margin">
            <#if model??>
                编辑公告
            <#else>
                添加公告
            </#if>
        </h3>
    </div>
</div>
<div class="row">
    <div class="col-md-12">
        <section class="panel">
            <header class="panel-heading">填写公告信息</header>
            <div class="panel-body">
                <form class="form-horizontal" action="../announcement<#if model??>/${model.id!}</#if>.html<#if model??>?_method=PUT</#if>" method="post">
                    <#if model??>
                        <input type="hidden" name="id" value="${(model.id)!}">
                    </#if>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">标题</label>
                        <div class="col-sm-10">
                            <input class="form-control" name="title" id="title"  placeholder="请输入标题" rows="10" value="${(model.title)!}">
                            <label class="error-information"></label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">内容</label>
                        <div class="col-sm-10">
                            <textarea class="form-control" name="detail" id="detail" type="text" placeholder="请输入内容" rows="10">${(model.detail)!}</textarea>
                            <label class="error-information"></label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">联系方式</label>
                        <div class="col-sm-10">
                            <input class="form-control" name="contact" id="contact" type="text" placeholder="请输入联系方式" value="${(model.contact)!}">
                            <label class="error-information"></label>
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
<script type="text/javascript">
    $(document).ready(function() {
        $(".btn-reset").click(function() {
            location.href = "${contextPath}/admin/announcement.html";
        });

        //表单验证
        var detail = $("#detail");
        var title = $("input[name='title']");
        var contact = $("input[name='contact']");
        var validateFunc = function(dom){
            var validator = new Validator();
            validator.add(detail,[
                {strategy:'isNotEmpty',errorMsg:'内容不能为空!'},
            ]);
            validator.add(title,[
                {strategy:'isNotEmpty',errorMsg:'标题不能为空!'},
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
    <li><a href="${contextPath}/admin/announcement.html">公告</a></li>
    <li class="active">
        <#if model??>
            编辑公告
        <#else>
            添加公告
        </#if>
    </li>
</ol>
</#macro>

<@layout title=overrideTitle style=defaultStyle content=overrideContent script=overrideScript nav=overrideNav>
</@layout>