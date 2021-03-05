<!DOCTYPE html>
<html lang="${lang}">
<#include "head.ftl">
<body>
<#include "header.ftl">
<main class="container-fluid bg-light">
	<h1>${p.getTitle(lang)!""}</h1>
	<p>${p.getDescription(lang)}</p>
	<section>
	<h2>${pri[lang]}</h2>
	<div class="row row-cols-1 row-cols-md-4">
		<#list principles>
		<#items as e>
			<div class="col mb-3">
			<section class="card bg-light h-100">
				<div class="card-body bg-dark">
					<span class="card-title">
					<a href="/eif3/principle/${e.getLocalId()}.${lang}.html" class="stretched-link text-white">
					${e.getTitle(lang)}</a>
					</span>
				</div>
			</section>
			</div>
		</#items>
		</#list>
	</div>
	</section>
	<section>
	<h2>${niv[lang]}</h2>
	<div class="row row-cols-1 row-cols-md-4">
		<#list levels>
		<#items as l>
			<div class="col mb-3">
			<section class="card bg-light h-100">
				<div class="card-body bg-dark">
					<span class="card-title">
					<a href="/eif3/level/${l.getLocalId()}.${lang}.html" class="stretched-link text-white">
					${l.getTitle(lang)}</a>
					</span>
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
