<#include "layout-nowrapper.ftl">

<#macro overrideTitle>
    登陆 -
</#macro>

<#macro overrideContent>
<div class="row">
    <div
            class="col-xs-10 col-xs-offset-1 col-sm-6 col-sm-offset-3 col-md-4 col-md-offset-4">
        <section class="panel panel-default">
            <header class="panel-heading">登录</header>
            <div class="bg-white user pd-md">
                <h6>
                    <strong>欢迎。</strong>请登录!
                </h6>
                <form role="form" action="${contextPath}/admin/login.do" method="post">
                    <input class="form-control mg-b-sm" name="username" placeholder="用户名" autofocus="" type="text">
                    <input class="form-control" name="password" placeholder="密码" type="password">
                    <label class="checkbox pull-left">
                        <input name="remember-me" value="true" type="checkbox">记住我
                    </label>
                    <!-- <div class="text-right mg-b-sm mg-t-sm">
                        <a href="javascript:;">Forgot password?</a>
                    </div> -->
                    <button class="btn btn-info btn-block" type="submit">登录</button>
                </form>
            </div>
        </section>
    </div>
</div>
</#macro>

<@layoutNowrapper title=overrideTitle style=defaultStyle content=overrideContent script=defaultScript>
</@layoutNowrapper>
