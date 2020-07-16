<!DOCTYPE html>
<html>
<#include "head.ftl">
<body>
<#include "header.ftl">
<main class="container-fluid bg-light">
	<h1>Legislation: ${legislation.getTitle(lang)}</h1>
	<p>Date: ${legislation.getDate()}</p>
	<p>${legislation.getDescription(lang)}</p>
	<p><a href="${legislation.getWebsite()}" class="btn btn-primary">Text</a></p>
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
