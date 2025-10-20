<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Spring Hibernate Demo</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        .nav { margin-bottom: 30px; }
        .nav a { margin-right: 20px; text-decoration: none; color: #007bff; }
        .nav a:hover { text-decoration: underline; }
        h1 { color: #333; }
    </style>
</head>
<body>
    <div class="nav">
        <a href="${pageContext.request.contextPath}/">Accueil</a>
        <a href="${pageContext.request.contextPath}/products">Produits</a>
        <a href="${pageContext.request.contextPath}/categories">Catégories</a>
    </div>
    
    <h1>Bienvenue dans l'application Spring Hibernate Demo</h1>
    
    <p>Cette application démontre l'intégration de Spring MVC avec Hibernate pour la gestion de produits et catégories.</p>
    
    <h2>Fonctionnalités disponibles:</h2>
    <ul>
        <li><a href="${pageContext.request.contextPath}/products">Gestion des produits</a></li>
        <li><a href="${pageContext.request.contextPath}/categories">Gestion des catégories</a></li>
    </ul>
</body>
</html>