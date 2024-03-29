<#ftl encoding="utf-8">
<#assign fod = { "nl": "FOD BOSA", "fr": "SPF BOSA", "de": "FÖD BOSA", "en": "FPS BOSA" }>
<#assign use = { "nl": "Gebruiksvoorwaarden", "fr": "Conditions d'utilisation", "de": "Nutzungsbedingungen", "en": "Conditions of use" }>
<#assign priv = { "nl": "Privacyverklaring", "fr": "Déclaration de confidentialité", "de": "Datenschutzerklärung", "en": "Privacy statement" }>
<#assign wcag = { "nl": "Toegankelijkheidsverklaring", "fr": "Déclaration d'accessibilité", "de": "Erklärung zur Barrierefreiheit", "en": "Accessibility statement" }>

<footer>
	<nav class="navbar justify-content-center">
		<span>&copy; 2020 - 2022</span>
		<a href="http://bosa.belgium.be" class="nav-link">${fod[lang]}</a>
		<a href="http://github.com/belgif/www-belgif" class="nav-link">GitHub</a>
		<a href="/page/accessibility.${lang}.html" class="nav-link">${wcag[lang]}</a>
		<a href="/page/conditions.${lang}.html" class="nav-link">${use[lang]}</a>
		<a href="/page/privacy.${lang}.html" class="nav-link">${priv[lang]}</a>
	</nav>
</footer>