<!DOCTYPE html>
<html>
<#include "head.ftl">
<body>
<#include "header.ftl">
<main class="container-fluid bg-light">
	<div class="row">
		<div class="col">
			<section>
			<h1>Recommendation ${r.sequence}: ${r.getTitle(lang)}</h1>
			<p class="lead">${r.getDescription(lang)}</p>
			</section>
		</div>
	</div>
</main>
<#include "footer.ftl">
</body>
</html>
