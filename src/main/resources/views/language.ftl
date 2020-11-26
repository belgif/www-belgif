<!DOCTYPE html>
<html>
<#include "head.ftl">
<script>
	var lang = Cookies.get('lang');
	var langs = ["nl", "fr", "de", "en"];
	if (lang && langs.includes(lang)) {
		window.location.href = 'index.' + lang + '.html';
	}
</script>
<body class="language-selection-page">
<main class="container-fluid">
	<div class="logo">
		<img src="public/belgif.png" class="mx-auto d-block" alt="Belgif logo"/>
	</div>
	<div class="row bg-light">
	<div class="card-deck w-100 text-center mx-auto">
		<section class="card">
			<div class="card-body">
				<h2 class="card-title"><a href="index.nl.html" hreflang="nl" onclick="Cookies.set('lang', 'nl');">Nederlands</a></h2>
			</div>
		</section>
		<section class="card">
			<div class="card-body">
				<h2 class="card-title"><a href="index.fr.html" hreflang="fr" onclick="Cookies.set('lang', 'fr');">Fran&ccedil;ais</a></h2>
			</div>
		</section>
		<section class="card">
			<div class="card-body">
				<h2 class="card-title"><a href="index.de.html" hreflang="de" onclick="Cookies.set('lang', 'de');">Deutsch</a></h2>
			</div>
		</section>
		<section class="card">
			<div class="card-body">
				<h2 class="card-title"><a href="index.en.html" hreflang="en" onclick="Cookies.set('lang', 'en');">English</a></h2>
			</div>
		</section>
	</div>
	</div>
	<div class="footer-bg-image">
	</div>
</main>
</body>
</html>
