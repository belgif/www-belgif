<!DOCTYPE html>
<html lang="${lang}">
<#include "head.ftl">
<body>
<#include "header.ftl">
<main class="container-fluid">
	<h2>${p.getTitle(lang)}</h2>
	<p>${p.getDescription(lang)}</p>
	<div class="row row-cols-1 row-cols-md-3">
		<#list activities>
		<#items as a>
			<div class="col mb-4">
			<section class="card bg-light h-100">
				<div class="card-body">
					<h5 class="card-title">
					<a href="/page/activity/${a.localId}.${lang}.html" class="stretched-link">
					${a.getTitle(lang)}</a>
					</h5>
					<p class="lead">${a.getDescription(lang)}</p>
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
