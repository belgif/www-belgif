<#ftl encoding="utf-8">

<#assign date = { "nl": "Datum", "fr": "Date", "de": "Aktivitäten", "en": "Datum" }>

<!DOCTYPE html>
<html lang="${lang}">
<#include "head.ftl">
<body>
<#include "header.ftl">
<main class="container-fluid bg-light">
	<h1>${law[lang]}: ${p.getTitle(lang)}</h1>
	<p>${date[lang]}: ${p.getDate()}</p>
	<p>${p.getDescription(lang)}</p>
	<h2>${link[lang]}</h2>
	<#list links as l>
		<p><a href="${l.getId()}" class="ext">${l.getTitle(lang)}</a></p>
	</#list>
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
