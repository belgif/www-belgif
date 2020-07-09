<!DOCTYPE html>
<html>
<#include "head.ftl">
<body>
<#include "header.ftl">
<main class="container-fluid">
	<div class="row row-cols-1 row-cols-md-2">
		<div class="col mb-2">
		<section class="card be-legal h-100">
			<div class="card-body">
				<h3 class="card-title"><i class="fas fa-balance-scale"></i> ${legal.getTitle(lang)}</h3>
				<p class="lead">${legal.getDescription(lang)}</p>
			</div>
		</section>
		</div>
		<div class="col mb-2">
		<section class="card be-organisational h-100">
			<div class="card-body">
				<h3 class="card-title"><i class="fas fa-university"></i> ${organisational.getTitle(lang)}</h3>
				<p class="lead">${organisational.getDescription(lang)}</p>
			</div>
		</section>
		</div>
		<div class="col mb-2">
		<section class="card be-semantic h-100">
			<div class="card-body">
				<h3 class="card-title"><i class="far fa-file-code"></i> ${semantic.getTitle(lang)}</h3>
				<p class="lead">${semantic.getDescription(lang)}</p>
			</div>
		</section>
		</div>
		<div class="col mb-2">
		<section class="card be-technical h-100">
			<div class="card-body">
				<h3 class="card-title"><i class="fas fa-server"></i> ${technical.getTitle(lang)}</h3>
				<p class="lead">${technical.getDescription(lang)}</p>
			</div>
		</section>
		</div>
	</div>

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
</main>
<#include "footer.ftl">
</body>
</html>
