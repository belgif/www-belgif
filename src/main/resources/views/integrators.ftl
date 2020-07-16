<!DOCTYPE html>
<html>
<#include "head.ftl">
<body>
<#include "header.ftl">
<main class="container-fluid bg-light">
	<h1>${p.getTitle(lang)}</h1>
	<p>${p.getDescription(lang)}</p>
	<div class="row row-cols-1 row-cols-md-3">
		<#list integrators>
		<#items as org>
			<div class="col mb-4">
			<section class="card bg-light h-100">
				<div class="card-img-top text-center">
					<a href="${org.getWebsite()}" class="stretched-link">
					<img src="/images/${org.getLogo()}" alt="logo"/>
				</a>
				</div>
				<div class="card-body">
					<h5 class="card-title">${org.getTitle(lang)}</h5>
					<p class="lead">${org.getDescription(lang)}</p>
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
