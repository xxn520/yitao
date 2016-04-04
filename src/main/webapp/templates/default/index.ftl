<#include "layout.ftl">

<#macro overrideStyle>
</#macro>

<#macro overrideScript>
</#macro>

<#macro overrideContent>
<div>content overriden</div>
</#macro>

<@layout style=overrideStyle content=overrideContent script=overrideScript>
</@layout>