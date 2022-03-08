<#ftl encoding="utf-8">

<#assign name = { "nl": "Naam", "fr": "Nom", "de": "Name", "en": "Name" }>
<#assign ver = { "nl": "Versie", "fr": "Version", "de": "Version", "en": "Version" }>
<#assign area = { "nl": "Domein", "fr": "Domaine", "de": "Domäne", "en": "Domain" }>

<!DOCTYPE html>
<html lang="${lang}">
<#include "head.ftl">
<body>
<#include "header.ftl">
<main class="container-fluid bg-light">
	<h2>${p.getTitle(lang)}</h2>
	<p>${p.getDescription(lang)}</p>
	<div class="table-responsive">
		<table class="table table-sm table-striped">
		<thead class="bg-dark text-light">
			<tr><th scope="col">${name[lang]}</th>
				<th scope="col">${ver[lang]}</th>
				<th scope="col">${area[lang]}</th></tr>
		</thead>
		<tbody>
			<#list specifications as s>
			    <tr>
					<td><a href="/page/specification/${s.localId}.${lang}.html">${s.getTitle(lang)}</a></td>
					<td>${s.version!""}</td>
					<td>${s.getSubject(lang)}</td>
				</tr>
			</#list>
		</tbody>
		</table>
	</div>
</main>
<#include "footer.ftl">
</body>
</html>
