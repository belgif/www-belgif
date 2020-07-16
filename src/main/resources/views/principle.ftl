<!DOCTYPE html>
<html>
<#include "head.ftl">
<body>
<#include "header.ftl">
<main class="container-fluid">
	<h1>Principle ${p.sequence} ${p.getTitle(lang)}</h1>
	<h3>Recommendations</h3>
	<div class="card-deck">
		<#list recommendations>
		<#items as r>
			<section class="card bg-light col mb-4">
				<div class="card-body">
					<h5 class="card-title">${r.sequence}. ${r.getTitle(lang)}</h5>
					<p class="card-text">${r.getDescription(lang)}</p>
					<a href="/eif3/recommendation/${r.localId}" class="btn btn-primary stretched-link">More</a>
				</div>
			</section>
		</#items>
		</#list>
	</div>
</main>
<#include "footer.ftl">
</body>
</html>
