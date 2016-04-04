<#macro overrideContent>
<div class="error">
    <div class="error-code"><b>500</b></div>
    <div class="mg-b-lg error-title">出错啦</div>
    <p>
        ${error.cause.cause}<br/>
		<#list error.stackTrace as trace>
		    ${trace.className}${trace.lineNumber}<br>
		</#list>
        <a href="${request.contextPath}/index.html">返回首页</a>
        <a href="javascript:history.back();">上一页</a>
    </p>
</div>
</#macro>

<#macro overrideScript>
</#macro>

<@layout style=overrideStyle content=overrideContent script=overrideScript>
</@layout>
