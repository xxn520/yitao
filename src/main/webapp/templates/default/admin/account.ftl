<#include "layout.ftl">

<#macro overrideTitle>
	编辑登录 -
</#macro>

<#macro overrideContent>
<div class="row mg-b">
    <div class="col-xs-12">
        <h3 class="no-margin"><#if model??>编辑</#if>登录</h3>
    </div>
</div>
<div class="row">
    <div class="col-md-12">
        <section class="panel">
            <ul class="nav nav-tabs">
                <li><a href="${contextPath}/admin/user/${request.getParameter("uid")!}.html">用户信息</a></li>
                <li class="active"><a href="javascript:void(0)">登录信息</a></li>
            </ul>
            <div class="panel-body">
                <form class="form-horizontal" action="account.html?uid=${request.getParameter("uid")!}" method="post">
                    <input type="hidden" name="uid" value="${request.getParameter("uid")!}">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">登录名</label>
                        <div class="col-sm-10">
                            <input class="form-control" name="username" type="text" placeholder="请输入登录名" required="required" value="${(model.username)!}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">密码</label>
                        <div class="col-sm-10">
                            <input class="form-control" name="password" type="password" placeholder="请输入密码" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">验证密码</label>
                        <div class="col-sm-10">
                            <input class="form-control" name="check_password" type="password" placeholder="再输入一次密码" required>
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
    <li><a href="${contextPath}/admin/index.html">首页</a></li>
    <li><a href="${contextPath}/admin/user.html">用户</a></li>
    <li class="active">编辑登录</li>
</ol>
</#macro>

<@layout title=overrideTitle style=defaultStyle content=overrideContent script=overrideScript nav=overrideNav>
</@layout>