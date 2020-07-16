<!DOCTYPE html>
<html>
<#include "head.ftl">
<body>
<#include "header.ftl">
<main class="container-fluid bg-light">
	<h1>EIF 3</h1>
	<section>
	<h2>Interoperability Levels</h2>
	<div class="row row-cols-1 row-cols-md-3">
		<#list levels>
		<#items as l>
			<div class="col mb-3">
			<section class="card bg-light h-100">
				<div class="card-body">
					<h5 class="card-title">
					<a href="/eif3/principle/${l.getLocalId()}.${lang}.html" class="stretched-link">
					${l.getTitle(lang)}</a>
					</h5>
				</div>
			</section>
			</div>
		</#items>
		</#list>
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
					<a href="/eif3/principle/${p.getLocalId()}.${lang}.html" class="stretched-link">
					${p.getSequence()}. ${p.getTitle(lang)}</a>
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
