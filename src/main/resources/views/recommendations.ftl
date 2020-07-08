<!DOCTYPE html>
<html>
<#include "head.ftl">
<body>
<#include "header.ftl">
<main class="container">
	<h2>Recommendations</h2>
	<#list recommendations>
	<div class="row">
		<#items as r>
			<section class="card bg-light col mb-4">
				<div class="card-body">
			<h2><i class="fas fa-university"></i> ${r.sequence} ${r.getTitle(lang)}</h2>
			</section>
		</div>
		</#items>
	</div>
	</#list>
</main>
<#include "footer.ftl">
</body>
</html>
