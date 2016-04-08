<#include "layout.ftl">

<#macro overrideTitle>
    没有权限 -
</#macro>

<#macro overrideContent>
<div class="error">
    <div class="error-code">403</div>
    <div class="mg-b-lg error-title">你没有权限访问哟</div>
    <p>
        吹个球，吹个大气球<br>
        <a href="${request.contextPath}/index.html">返回首页</a>
        <a href="javascript:history.back();">上一页</a>
    </p>
</div>
</#macro>

<@layout title=overrideTitle style=defaultStyle content=overrideContent script=defaultScript>
</@layout>