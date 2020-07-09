<!DOCTYPE html>
<html>
<#include "head.ftl">
<body>
<#include "header.ftl">
<main class="container-fluid">
	<div class="row">
		<div class="col d-flex">
			<section class="jumbotron be-legal">
			<h2><i class="fas fa-balance-scale"></i> ${legal.getTitle(lang)}</h2>
			<p class="lead">${legal.getDescription(lang)}</p>
			</section>
		</div>
		<div class="col d-flex">
			<section class="jumbotron be-organisational">
			<h2><i class="fas fa-university"></i> ${organisational.getTitle(lang)}</h2>
			<p class="lead">${organisational.getDescription(lang)}</p>
			</section>
		</div>
	</div>
	<div class="row">
		<div class="col d-flex">
			<section class="jumbotron be-semantic">
			<h2><i class="far fa-file-code"></i> ${semantic.getTitle(lang)}</h2>
			<p class="lead">${semantic.getDescription(lang)}</p>
			</section>
		</div>
		<div class="col d-flex">
			<section class="jumbotron be-technical">
			<h2><i class="fas fa-server"></i> ${technical.getTitle(lang)}</h2>
			<p class="lead">${technical.getDescription(lang)}</p>
			</section>
		</div>
	</div>
	<div class="row">
		<div class="col">
			<a href="/eif3/principle" class="text-decoration-none">
			<section class="jumbotron" style="background-color: #993366; color: #FFFFFF">
			<h2><i class="far fa-file-code"></i> Principles</h2>
			</section>
			</a>
		</div>
		<div class="col">
			<a href="/eif3/recommendation" class="text-decoration-none">
			<section class="jumbotron" style="background-color: #993366; color: #FFFFFF">
			<h2><i class="far fa-file-code"></i> Recommendations</h2>
			</section>
			</a>
		</div>
	</div>
</main>
<#include "footer.ftl">
</body>
</html>
