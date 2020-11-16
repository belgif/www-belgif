<#ftl encoding="utf-8">
<#assign home = { "nl": "Home", "fr": "Acceuil", "de": "Home", "en": "Home" }>
<#assign rec = { "nl": "Aanbevelingen", "fr": "Recommandations", "de": "Empfehlungen", "en": "Recommendations" }>
<#assign comp = { "nl": "Componenten", "fr": "Composants", "de": "Komponenten", "en": "Components" }>
<#assign reg = { "nl": "Registers", "fr": "Registres", "de": "Register", "en": "Registries" }>
<#assign int = { "nl": "Integratoren", "fr": "Intégrateurs", "de": "Integratoren", "en": "Integrators" }>
<#assign c = { "nl": "Contact", "fr": "Contact", "de": "Kontakt", "en": "Contact" }>

<header>
	<nav id="lang-wrapper" class="nav nav-pills justify-content-end">
		<a href="#" hreflang="nl" class="nav-link <#if lang == 'nl'>active</#if>">NL</a>
		<a href="#" hreflang="fr" class="nav-link <#if lang == 'fr'>active</#if>">FR</a>
		<a href="#" hreflang="de" class="nav-link <#if lang == 'de'>active</#if>">DE</a>
		<a href="#" hreflang="en" class="nav-link <#if lang == 'en'>active</#if>">EN</a>
	</nav>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a href="#" class="navbar-brand"><img id="logo" src="/public/belgif.png" alt="Belgif logo"/></a>
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