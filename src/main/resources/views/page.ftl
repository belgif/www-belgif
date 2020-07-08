<!DOCTYPE html>
<html>
<#include "head.ftl">
<body>
<#include "header.ftl">
<main class="container-fluid">
	<div class="row">
		<div class="col">
			<section class="jumbotron">
			<h2>${p.getTitle(lang)}</h2>
			<p>${p.getDescription(lang)}</p>
			</section>
		</div>
	</div>
</main>
<#include "footer.ftl">
</body>
</html>
