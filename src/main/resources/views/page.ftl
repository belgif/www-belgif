<!DOCTYPE html>
<html lang="${lang}">
<#include "head.ftl">
<body>
<#include "header.ftl">
<main class="container-fluid bg-light">
	<div class="row">
		<div class="col">
			<section>
			<h2>${p.getTitle(lang)}</h2>
			<p>${p.getDescription(lang)}</p>
			</section>
		</div>
	</div>
</main>
<#include "footer.ftl">
</body>
</html>
