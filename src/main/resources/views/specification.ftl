<!DOCTYPE html>
<html>
<#include "head.ftl">
<body>
<#include "header.ftl">
<main class="container-fluid bg-light">
	<h1>Specification: ${specification.getTitle(lang)}</h1>
	<p>${specification.getAbstract(lang)}</p>
	<h2>Description</h2>
	<p>${specification.getDescription(lang)}</p>
	<h2>Link</h2>
	<a href="${specification.getWebsite()}">${specification.getWebsite()}</a>
</main>
<#include "footer.ftl">
</body>
</html>
