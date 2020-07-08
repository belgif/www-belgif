<!DOCTYPE html>
<html>
<#include "head.ftl">
<body>
<#include "header.ftl">
<main class="container-fluid">
	<#list principles>
	<#items as p>
		<#if p?is_odd_item>
			<div class="row">
		</#if>
			<div class="col-sm">
				<a href="/eif3/principle/${p.localId}">
				<section class="jumbotron">
				<h2><i class="far fa-compass"></i> ${p.sequence}. ${p.getTitle(lang)}</h2>
				</section>
				</a>
			</div>
		<#if p?is_even_item>
			</div>
		</#if>
	</#items>
	</#list>
</main>
<#include "footer.ftl">
</body>
</html>
