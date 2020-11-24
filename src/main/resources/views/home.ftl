<!DOCTYPE html>
<html>
<#include "head.ftl">
<body class="homepage">
<#include "header.ftl">
<main class="container-fluid">
	<div class="row row-cols-1 row-cols-md-2">
		<div class="col mb-2">
		<section class="card be-eif">
			<div class="card-body">
				<h1 class="card-title"><i class="fas fa-cubes"></i>
					<a href="/eif3/about.${lang}.html" class="stretched-link text-decoration-none">
					${eif.getTitle(lang)}</a>
				</h1>
				<p class="lead">${eif.getDescription(lang)}</p>
			</div>
		</section>
		</div>
		<div class="col mb-2">
		<section class="card be-specs h-100">
			<div class="card-body">
				<h1 class="card-title"><i class="far fa-file-code"></i>
					<a href="/page/specifications.${lang}.html" class="stretched-link text-decoration-none">
					${specs.getTitle(lang)}</a> 
				</h1>
				<p class="lead">${specs.getDescription(lang)}</p>
			</div>
		</section>
		</div>
		<div class="col mb-2">
		<section class="card be-activities h-100">
			<div class="card-body">
				<h1 class="card-title"><i class="fas fa-users"></i>
					<a href="/page/activities.${lang}.html" class="stretched-link text-decoration-none">
					${activities.getTitle(lang)}</a>
				</h1>
				<p class="lead">${activities.getDescription(lang)}</p>
			</div>
		</section>
		</div>
		<div class="col mb-2">
		<section class="card be-legal h-100">
			<div class="card-body">
				<h1 class="card-title"><i class="fas fa-balance-scale"></i> 
					<a href="/page/legislations.${lang}.html" class="stretched-link text-decoration-none">
					${legal.getTitle(lang)}</a>
				</h1>
				<p class="lead">${legal.getDescription(lang)}</p>
			</div>
		</section>
		</div>
	</div>
</main>
<#include "footer.ftl">
</body>
</html>
