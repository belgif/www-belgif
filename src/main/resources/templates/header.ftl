<#ftl encoding="utf-8">
<#assign home = { "nl": "Home", "fr": "Accueil", "de": "Home", "en": "Home" }>
<#assign rec = { "nl": "Aanbevelingen", "fr": "Recommandations", "de": "Empfehlungen", "en": "Recommendations" }>
<#assign comp = { "nl": "Componenten", "fr": "Composants", "de": "Komponenten", "en": "Components" }>
<#assign reg = { "nl": "Registers", "fr": "Registres", "de": "Register", "en": "Registries" }>
<#assign int = { "nl": "Integratoren", "fr": "Intégrateurs", "de": "Integratoren", "en": "Integrators" }>
<#assign c = { "nl": "Contact", "fr": "Contact", "de": "Kontakt", "en": "Contact" }>

<header>
	<nav id="lang-wrapper" class="nav nav-pills justify-content-end">
		<a href="${path}.nl.html" onclick="Cookies.set('lang', 'nl');" hreflang="nl" class="nav-link <#if lang == 'nl'>active</#if>">NL</a>
		<a href="${path}.fr.html" onclick="Cookies.set('lang', 'fr');" hreflang="fr" class="nav-link <#if lang == 'fr'>active</#if>">FR</a>
		<a href="${path}.de.html" onclick="Cookies.set('lang', 'de');" hreflang="de" class="nav-link <#if lang == 'de'>active</#if>">DE</a>
		<a href="${path}.en.html" onclick="Cookies.set('lang', 'en');" hreflang="en" class="nav-link <#if lang == 'en'>active</#if>">EN</a>
	</nav>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a href="#" class="navbar-brand"><img id="logo" src="/images/belgif.png" alt="Belgif logo"/></a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#menu" 
				aria-controls="menu" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div id="menu" class="collapse navbar-collapse justify-content-end" >
			<a class="nav-link font-weight-bold" href="/index.${lang}.html">${home[lang]}</a>
			<a class="nav-link font-weight-bold" href="/eif3/recommendations.${lang}.html">${rec[lang]}</a>
			<a class="nav-link font-weight-bold" href="/page/components.${lang}.html">${comp[lang]}</a>
			<a class="nav-link font-weight-bold" href="/page/registries.${lang}.html">${reg[lang]}</a>
			<a class="nav-link font-weight-bold" href="/page/integrators.${lang}.html">${int[lang]}</a>
			<a class="nav-link font-weight-bold" href="/page/contact.${lang}.html">${c[lang]}</a>
		</div>
	</nav>
</header>