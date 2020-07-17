<!DOCTYPE html>
<html>
<#include "head.ftl">
<body>
<#include "header.ftl">
<main class="container-fluid bg-light">
	<h1>Activity: ${activity.getTitle(lang)}</h1>
	<p>${activity.getDescription(lang)}</p>
	<p><a href="${activity.getWebsite()}" class="btn btn-primary">More info</a></p>
	<#if principles?has_content>
	<h2>Principles</h2>
	<#list principles as p>
		<a href="/eif3/principle/${p.getLocalId()}.${lang}.html" class="btn btn-secondary">${p.getSequence()}. ${p.getTitle(lang)}</a>
	</#list>
	</#if>
	<#if recommendations?has_content>
	<h2>Recommendations</h2>
	<#list recommendations as r>
		<a href="/eif3/recommendation/${r.getLocalId()}.${lang}.html" class="btn btn-primary">${r.getSequence()}. ${r.getTitle(lang)}</a>
	</#list>
	</#if>
</main>
<#include "footer.ftl">
</body>
</html>
