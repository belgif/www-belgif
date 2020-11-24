<!DOCTYPE html>
<html>
<#include "head.ftl">
<body>
<#include "header.ftl">
<main class="container-fluid bg-light">
	<h1>${act[lang]}: ${p.getTitle(lang)}</h1>
	<p>${p.getDescription(lang)}</p>
	<h2>${link[lang]}</h2>
	<p><a href="${p.website}" class="ext">${p.website}</a></p>
	<#if principles?has_content>
	<h2>${pri[lang]}</h2>
	<#list principles as p>
		<a href="/eif3/principle/${p.getLocalId()}.${lang}.html" class="btn btn-secondary">${p.getSequence()}. ${p.getTitle(lang)}</a>
	</#list>
	</#if>
	<#if recommendations?has_content>
	<h2>${home[lang]}</h2>
	<#list recommendations as r>
		<a href="/eif3/recommendation/${r.getLocalId()}.${lang}.html" class="btn btn-primary">${r.getSequence()}. ${r.getTitle(lang)}</a>
	</#list>
	</#if>
</main>
<#include "footer.ftl">
</body>
</html>
