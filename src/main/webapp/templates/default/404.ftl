{{<layout}}
{{$title}}
找不到啦 - 
{{/title}}
{{$content}}
<div class="error">
	<div class="error-code">404</div>
	<div class="mg-b-lg error-title">页面找不到啦</div>
	<p>
		大师兄，师父被妖怪抓走啦。<br>
		<a href="{{request.contextPath}}/index.html">返回首页</a>
		<a href="javascript:history.back();">上一页</a>
	</p>
</div>
{{/content}}
{{/layout}}