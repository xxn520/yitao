<#include "layout.ftl">

<#macro overrideTitle>
    <#if model??>
    编辑认证 -
    <#else>
    添加认证 -
    </#if>
</#macro>

<#macro overrideContent>
<div class="row mg-b">
    <div class="col-xs-12">
        <h3 class="no-margin">
            <#if model??>
                编辑认证
            <#else>
                添加认证
            </#if>
        </h3>
    </div>
</div>
<div class="row">
    <div class="col-md-12">
        <section class="panel">
            <header class="panel-heading">填写认证信息</header>
            <div class="panel-body">
                <form class="form-horizontal" action="../studentAuth<#if model??>/${model.id!}</#if>.html<#if model??>?_method=PUT</#if>" method="post">
                    <#if model??>
                        <input type="hidden" name="id" value="${(model.id)!}">
                    </#if>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">姓名</label>
                        <div class="col-sm-10">
                            <input class="form-control" name="name" id="name"  placeholder="请输入姓名" rows="10" value="${(model.name)!}">
                            <label class="error-information"></label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">学号</label>
                        <div class="col-sm-10">
                            <input class="form-control" name="sid" id="sid"  placeholder="请输入学号" rows="10" value="${(model.sid)!}">
                            <label class="error-information"></label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">状态</label>
                        <div class="col-sm-10" style="padding-top:5px;">
                            <label class="radio-inline">
                                <input type="radio" name="status" value="0"<#if (model.status)??&&model.status=='UNAUDITED'>checked</#if>>未认证
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="status" value="1"<#if (model.status)??&&model.status=='AUDIT_FAIL'>checked</#if>>认证未通过
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="status" value="2"<#if (model.status)??&&model.status=='AUDIT_SUCCESS'>checked</#if>>认证通过
                            </label>
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
<script type="text/javascript">
    $(document).ready(function() {
        $(".btn-reset").click(function() {
            location.href = "${contextPath}/admin/studentAuth.html";
        });

        //表单验证
        var sid = $("input[name='sid']");
        var name = $("input[name='name']");
        var validateFunc = function(dom){
            var validator = new Validator();
            validator.add(name,[
                {strategy:'isNotEmpty',errorMsg:'姓名不能为空!'},
            ]);
            validator.add(sid,[
                {strategy:'isNotEmpty',errorMsg:'学号不能为空!'},
            ]);
            var error= validator.start(dom);
            return error;
        };

        $('input').blur(function(){
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
    <li><a href="javascript:void(0)">认证</a></li>
    <li class="active">
        编辑认证
    </li>
</ol>
</#macro>

<@layout title=overrideTitle style=defaultStyle content=overrideContent script=overrideScript nav=overrideNav>
</@layout>