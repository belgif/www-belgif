<!DOCTYPE html>
<!DOCTYPE html>
<html>
<#include "head.ftl">
<body>
<#include "header.ftl">
<main class="container-fluid">
	<h2>${p.getTitle(lang)}</h2>
	<p>${p.getDescription(lang)}</p>
	<div class="row row-cols-1 row-cols-md-3">
		<#list integrators>
		<#items as org>
			<div class="col mb-4">
			<section class="card bg-light h-100">
				<a href="${org.getWebsite()}" class="stretched-link">
				<img src="/images/${org.getLogo()}" height="97" class="card-img-top" alt="logo"/>
				</a>
				<div class="card-body">
					<h5 class="card-title">${org.getName(lang)}</h5>
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
