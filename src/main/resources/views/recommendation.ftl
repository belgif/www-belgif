<!DOCTYPE html>
<html>
<#include "head.ftl">
<body>
<#include "header.ftl">
<main class="container-fluid bg-light">
	<h1>Recommendation ${recommendation.sequence}: ${recommendation.getTitle(lang)}</h1>
	<p>${recommendation.getDescription(lang)}</p>
	<#if legislations?has_content>
		<section>
		<h2>Legislation and agreements</h2>
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
						<a href="/page/legislation/${l.localId}.${lang}.html" class="stretched-link">
						${l.getTitle(lang)}</a>
						</h5>
						<p class="lead">${l.getDescription(lang)}</p>
					</div>
				</section>
				</div>
			</#items>
			</#list>
		</div>
		</section>
	</#if>
	<#if activities?has_content>
		<section>
		<h2>Activities</h2>
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
		</section>
	</#if>
</main>
<#include "footer.ftl">
</body>
</html>
