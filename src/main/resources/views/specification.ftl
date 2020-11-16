<!DOCTYPE html>
<html>
<#include "head.ftl">
<body>
<#include "header.ftl">
<main class="container-fluid bg-light">
	<h1>Specification: ${p.getTitle(lang)}</h1>
	<p>${p.getDescription(lang)}</p>
	<h2>Description</h2>
	<p>${p.getAbstract(lang)}</p>
	<h2>Link</h2>
	<a href="${p.getWebsite()}">${p.getWebsite()}</a>
</main>
<#include "footer.ftl">
</body>
</html>
