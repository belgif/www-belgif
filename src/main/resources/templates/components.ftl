<#ftl encoding="utf-8">

<#assign name = { "nl": "Naam", "fr": "Nom", "de": "Name", "en": "Name" }>
<#assign desc = { "nl": "Beschrijving", "fr": "Description", "de": "Name", "en": "Beschreibung" }>

<!DOCTYPE html>
<html lang="${lang}">
<#include "head.ftl">
<body>
<#include "header.ftl">
<main class="container-fluid bg-light">
	<h2>${p.getTitle(lang)}</h2>
	<p>${p.getDescription(lang)}</p>
	<h2>Open Source</h2>
	<div class="table-responsive">
		<table class="table table-sm table-striped">
		<thead class="bg-dark text-light">
			<tr><th scope="col">${name[lang]}</th>
				<th scope="col">${desc[lang]}</th>
		</thead>
		<tbody>
			<#list components as c>
			    <tr>
					<td><a href="${c.repository}">${c.getTitle(lang)}</a></td>
					<td>${c.getDescription(lang)}</td>
				</tr>
			</#list>
		</tbody>
		</table>
	</div>
</main>
<#include "footer.ftl">
</body>
</html>
