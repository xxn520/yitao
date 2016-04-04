{{<layout}}
{{$title}}
出错啦 - 
{{/title}}
{{$content}}
<div class="error">
	<div class="error-code"><b>500</b></div>
	<div class="mg-b-lg error-title">出错啦</div>
	<p>
		{{error.cause.cause}}<br/>{{#error.stackTrace}}{{className}} {{lineNumber}}<br>{{/error.stackTrace}}
		<a href="{{request.contextPath}}/index.html">返回首页</a>
		<a href="javascript:history.back();">上一页</a>
	</p>
</div>
{{/content}}
{{/layout}}