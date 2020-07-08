<!DOCTYPE html>
<html>
<#include "head.ftl">
<body>
<#include "header.ftl">
<main class="container-fluid">
	<div class="row">
		<div class="col">
			<section class="jumbotron">
			<h2><i class="fas fa-university"></i> ${r.sequence} ${r.getTitle(lang)}</h2>
			</section>
		</div>
	</div>
</main>
<#include "footer.ftl">
</body>
</html>
