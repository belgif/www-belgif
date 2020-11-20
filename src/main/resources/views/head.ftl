<#ftl encoding="utf-8">
<#assign act = { "nl": "Activiteiten", "fr": "Activités", "de": "Aktivitäten", "en": "Activities" }>
<#assign pri = { "nl": "Principes", "fr": "Principes", "de": "Prinzipien", "en": "Principles" }>
<#assign rec = { "nl": "Aanbevelingen", "fr": "Recommandations", "de": "Empfehlungen", "en": "Recommendations" }>

<head>
<meta charset='UTF-8'>
<!-- <link rel="stylesheet" type="text/css" href="/public/style.css" /> -->
<link rel="stylesheet" type="text/css" href="/webjars/font-awesome/5.13.0/css/all.min.css" />
<link rel="stylesheet" type="text/css" href="/webjars/bootstrap/4.5.0/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="/public/style.css" />
<script src="/webjars/jquery/3.5.1/jquery.min.js"></script>
<script src="/webjars/bootstrap/4.5.0/js/bootstrap.bundle.min.js"></script>
<#if p?has_content>
	<title>Belgif | ${p.getTitle(lang)}</title>
<#else>
	<title>Belgif</title>
</#if>