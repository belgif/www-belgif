<!DOCTYPE html>
<html>
<#include "head.ftl">
<body>
<#include "header.ftl">
<main class="container-fluid bg-light">
	<h1>EIF 3</h1>
	<section>
	<h2>Interoperability Levels</h2>
	<div class="card-deck">
		<section class="card be-legal">
			<div class="card-body">
				<h3 class="card-title"><i class="fas fa-balance-scale"></i> ${legal.getTitle(lang)}</h3>
				<p class="lead">${legal.getDescription(lang)}</p>
			</div>
		</section>
		<section class="card be-organisational">
			<div class="card-body">
				<h3 class="card-title"><i class="fas fa-university"></i> ${organisational.getTitle(lang)}</h3>
				<p class="lead">${organisational.getDescription(lang)}</p>
			</div>
		</section>
		<section class="card be-semantic">
			<div class="card-body">
				<h3 class="card-title"><i class="far fa-file-code"></i> ${semantic.getTitle(lang)}</h3>
				<p class="lead">${semantic.getDescription(lang)}</p>
			</div>
		</section>
		<section class="card be-technical">
			<div class="card-body">
				<h3 class="card-title"><i class="fas fa-server"></i> ${technical.getTitle(lang)}</h3>
				<p class="lead">${technical.getDescription(lang)}</p>
			</div>
		</section>
	</div>
	</section>
	<section>
	<h2>Gov</h2>
	<div class="card-deck">
		<section class="card be-governance">
			<div class="card-body">
				<h3><i class="fas fa-gavel"></i> ${governance.getTitle(lang)}</h3>
				<p class="lead">${governance.getDescription(lang)}</p>
			</div>
		</section>
		<section class="card be-integration">
			<div class="card-body">
				<h3><i class="fas fa-sitemap"></i> ${integration.getTitle(lang)}</h3>
				<p class="lead">${integration.getDescription(lang)}</p>
			</div>
		</section>
	</div>
	</section>
	<section>
	<h2>Principles</h2>
	<div class="row row-cols-1 row-cols-md-3">
		<#list principles>
		<#items as p>
			<div class="col mb-4">
			<section class="card bg-light h-100">
				<div class="card-body">
					<h5 class="card-title">
					<a href="/eif3/principle/${p.localId}.${lang}.html" class="stretched-link">
					${p.sequence}. ${p.getTitle(lang)}</a>
					</h5>
				</div>
			</section>
			</div>
		</#items>
		</#list>
	</div>
	</section>
</main>
<#include "footer.ftl">
</body>
</html>
