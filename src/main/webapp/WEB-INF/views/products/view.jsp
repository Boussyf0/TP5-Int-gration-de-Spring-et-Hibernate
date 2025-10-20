<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Détails du Produit</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        .nav { margin-bottom: 30px; }
        .nav a { margin-right: 20px; text-decoration: none; color: #007bff; }
        .nav a:hover { text-decoration: underline; }
        .detail { margin-bottom: 10px; }
        .label { font-weight: bold; }
        .btn { padding: 10px 20px; margin: 5px; text-decoration: none; border-radius: 3px; }
        .btn-primary { background-color: #007bff; color: white; }
        .btn-secondary { background-color: #6c757d; color: white; }
    </style>
</head>
<body>
    <div class="nav">
        <a href="${pageContext.request.contextPath}/">Accueil</a>
        <a href="${pageContext.request.contextPath}/products">Produits</a>
        <a href="${pageContext.request.contextPath}/categories">Catégories</a>
    </div>

    <h1>Détails du Produit</h1>
    
    <div class="detail">
        <span class="label">ID:</span> ${product.id}
    </div>
    
    <div class="detail">
        <span class="label">Nom:</span> ${product.name}
    </div>
    
    <div class="detail">
        <span class="label">Prix:</span> ${product.price}€
    </div>
    
    <div class="detail">
        <span class="label">Catégorie:</span> 
        <c:choose>
            <c:when test="${product.category != null}">
                <a href="${pageContext.request.contextPath}/categories/${product.category.id}">${product.category.name}</a>
            </c:when>
            <c:otherwise>
                Aucune catégorie
            </c:otherwise>
        </c:choose>
    </div>
    
    <div style="margin-top: 20px;">
        <a href="${pageContext.request.contextPath}/products/${product.id}/edit" class="btn btn-primary">Modifier</a>
        <a href="${pageContext.request.contextPath}/products" class="btn btn-secondary">Retour à la liste</a>
    </div>
</body>
</html>