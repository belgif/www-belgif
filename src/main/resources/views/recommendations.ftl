<!DOCTYPE html>
<html>
<#include "head.ftl">
<body>
<#include "header.ftl">
<main class="container-fluid">
	<h2>Recommendations</h2>
	<div class="row row-cols-1 row-cols-md-4">
		<#list recommendations>
		<#items as r>
			<div class="col mb-4">
			<section class="card bg-light h-100">
				<div class="card-body">
					<h5 class="card-title">${r.sequence}. ${r.getTitle(lang)}</h5>
					<p class="lead">${r.getDescription(lang)}</p>
					<a href="/eif3/recommendation/${r.localId}" class="btn btn-primary stretched-link">More</a>
				</div>
			</section>
			</div>
		</#items>
		</#list>
	</div>
</main>
<#include "footer.ftl">
</body>
</html>