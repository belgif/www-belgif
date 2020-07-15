<!DOCTYPE html>
<html>
<#include "head.ftl">
<body>
<#include "header.ftl">
<main class="container-fluid">
	<h2>Agreements and legislation</h2>
	<div class="row row-cols-1 row-cols-md-3">
		<#list legislations>
		<#items as l>
			<div class="col mb-4">
			<section class="card bg-light h-100">
				<div class="card-header">
					${l.getDate()}
				</div>
				<div class="card-body">
					<h5 class="card-title">
					<a href="/legislation/${l.localId}.${lang}.html" class="stretched-link">
					${l.getTitle(lang)}</a>
					</h5>
					<p class="lead">${l.getDescription(lang)}</p>
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
