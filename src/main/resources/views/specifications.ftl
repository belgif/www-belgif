<!DOCTYPE html>
<!DOCTYPE html>
<html>
<#include "head.ftl">
<body>
<#include "header.ftl">
<main class="container-fluid bg-light">
	<h2>Specifications</h2>
	<div class="table-responsive">
		<table class="table table-sm">
		<thead>
			<tr><th scope="col">Name</th>
				<th scope="col">Version</th>
				<th scope="col">Area</th></tr>
		</thead>
		<tbody>
			<#list specifications>
			<#items as s>
			    <tr>
					<td><a href="/page/specification/${s.localId}.${lang}.html">${s.getTitle(lang)}</a></td>
					<td></td>
					<td>${s.getAbstract(lang)}</td>
				</tr>
		</#items>
		</#list>
		</tbody>
		</table>
	</div>
</main>
<#include "footer.ftl">
</body>
</html>
