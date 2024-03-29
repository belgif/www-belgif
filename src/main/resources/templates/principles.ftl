<!DOCTYPE html>
<html lang="${lang}">
<#include "head.ftl">
<body>
<#include "header.ftl">
<main class="container-fluid">
	<h2>${pri[lang]}</h2>
	<div class="row row-cols-1 row-cols-md-3">
		<#list principles as p>
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
		</#list>
	</div>
</main>
<#include "footer.ftl">
</body>
</html>
