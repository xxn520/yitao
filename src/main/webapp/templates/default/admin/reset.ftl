<#include "layout.ftl">

<#macro overrideTitle>
	重设密码 -
</#macro>

<#macro overrideContent>
<div class="row mg-b">
    <div class="col-xs-12">
        <h3 class="no-margin">重设密码</h3>
    </div>
</div>
<div class="row">
    <div class="col-md-12">
        <section class="panel">
            <ul class="nav nav-tabs">
                <li><a href="${contextPath}/admin/profile.html">账号设置</a></li>
                <li class="active"><a href="javascript:void(0)">重设密码</a></li>
            </ul>
            <div class="panel-body">
                <form class="form-horizontal" action="reset.html" method="post">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">旧密码</label>
                        <div class="col-sm-10">
                            <input class="form-control" name="oldPassword" type="password" required="required"  placeholder="请输入旧密码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">新密码</label>
                        <div class="col-sm-10">
                            <input class="form-control" name="password" type="password" required="required" placeholder="请输入新密码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">验证密码</label>
                        <div class="col-sm-10">
                            <input class="form-control" name="check_password" type="password" required="required" placeholder="再输入一次新密码">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-success">提交</button>
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
        <#if (request.attr.successMessage)??>
        toastr.success("${request.attr.successMessage}, undefined, {
            positionClass: "toast-top-right"
        });
		</#if>
		<#if (request.attr.errorMessage)??>
        toastr.error("${request.attr.errorMessage}", undefined, {
            positionClass: "toast-top-right"
        });
        </#if>
        $("form").submit(function() {
            if (this.elements["password"].value != this.elements["check_password"].value) {
                toastr.error('两次输入的密码不一致哟。');
                return false;
            }
            return true;
        });
    });
</script>
</#macro>

<#macro overrideNav>
<ol class="breadcrumb no-margin">
    <li><a href="${contextPath}}/admin/index.html">首页</a></li>
    <li class="active">重设密码</li>
</ol>
</#macro>

<@layout title=overrideTitle style=defaultStyle content=overrideContent script=overrideScript nav=overrideNav>
</@layout>
