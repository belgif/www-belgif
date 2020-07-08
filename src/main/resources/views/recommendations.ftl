<!DOCTYPE html>
<html>
<#include "head.ftl">
<body>
<#include "header.ftl">
<main class="container">
	<#list recommendations>
	<div class="row">
		<#items as r>
		<div class="col-sm">
			<section class="jumbotron">
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
