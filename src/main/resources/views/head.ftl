<#ftl encoding="utf-8">

<#assign act = { "nl": "Activiteiten", "fr": "Activités", "de": "Aktivitäten", "en": "Activities" }>
<#assign desc = { "nl": "Beschrijving", "fr": "Description", "de": "Beschreibung", "en": "Description" }>
<#assign law = { "nl": "Wetgeving", "fr": "Législation", "de": "Vorschriften", "en": "Legislation" }>
<#assign link = { "nl": "Link", "fr": "Lien", "de": "Link", "en": "Link" }>
<#assign niv = { "nl": "Interoperabiliteitsniveaus", "fr": "Niveaux de l'interopérabilité", "de": "Interoperabilitätsebenen", "en": "Interoperability Levels" }>
<#assign niv1 = { "nl": "Interoperabiliteitsniveau", "fr": "Niveau de l'interopérabilité", "de": "Interoperabilitätsebene", "en": "Interoperability Level" }>
<#assign pri = { "nl": "Principes", "fr": "Principes", "de": "Prinzipien", "en": "Principles" }>
<#assign rec = { "nl": "Aanbevelingen", "fr": "Recommandations", "de": "Empfehlungen", "en": "Recommendations" }>
<#assign rec1 = { "nl": "Aanbeveling", "fr": "Recommandation", "de": "Empfehlung", "en": "Recommendation" }>
<#assign spec1 = { "nl": "Specificatie", "fr": "Spécification", "de": "Spezifikation", "en": "Specification" }>

<head>
<meta charset='UTF-8'>
<link rel="stylesheet" type="text/css" href="/webjars/font-awesome/5.15.4/css/all.min.css" />
<link rel="stylesheet" type="text/css" href="/webjars/bootstrap/4.6.0-1/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="/public/style.css" />
<script src="/webjars/jquery/3.6.0/jquery.min.js"></script>
<script src="/webjars/js-cookie/2.2.1/js.cookie.min.js"></script>
<script src="/webjars/bootstrap/4.6.0-1/js/bootstrap.bundle.min.js"></script>

<#if p?has_content>
	<title>Belgif | ${p.getTitle(lang)}</title>
<#else>
	<title>Belgif</title>
</#if>