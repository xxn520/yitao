<#include "layout.ftl">

<#macro overrideStyle>
</#macro>

<#macro overrideContent>
<div>content overriden</div>
</#macro>

<#macro overrideScript>
</#macro>

<@layout style=overrideStyle content=overrideContent script=overrideScript>
</@layout>