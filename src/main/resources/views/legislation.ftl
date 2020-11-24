<!DOCTYPE html>
<html>
<#include "head.ftl">
<body>
<#include "header.ftl">
<main class="container-fluid bg-light">
	<h1>${law[lang]}: ${p.getTitle(lang)}</h1>
	<p>Date: ${p.getDate()}</p>
	<p>${p.getDescription(lang)}</p>
	<p><a href="${p.getWebsite()}" class="btn btn-primary">Text</a></p>
	<#if principles?has_content>
	<h2>${pri[lang]}</h2>
	<#list principles as e>
		<a href="/eif3/principle/${e.getLocalId()}.${lang}.html" class="btn btn-secondary">${e.getSequence()}. ${e.getTitle(lang)}</a>
	</#list>
	</#if>
	<#if recommendations?has_content>
	<h2>${rec[lang]}</h2>
	<#list recommendations as r>
		<a href="/eif3/recommendation/${r.getLocalId()}.${lang}.html" class="btn btn-primary">${r.getSequence()}. ${r.getTitle(lang)}</a>
	</#list>
	</#if>
</main>
<#include "footer.ftl">
</body>
</html>
