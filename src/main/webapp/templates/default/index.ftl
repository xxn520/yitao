<#include "layout.ftl">

<#macro overrideTitle>
    首页 -
</#macro>

<#macro overrideContent>
<div>content overriden</div>
</#macro>

<@layout title=overrideTitle style=defaultStyle content=overrideContent script=defaultScript>
</@layout>