<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Détails de la Catégorie</title>
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
        table { border-collapse: collapse; width: 100%; margin-top: 20px; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
    </style>
</head>
<body>
    <div class="nav">
        <a href="${pageContext.request.contextPath}/">Accueil</a>
        <a href="${pageContext.request.contextPath}/products">Produits</a>
        <a href="${pageContext.request.contextPath}/categories">Catégories</a>
    </div>

    <h1>Détails de la Catégorie</h1>
    
    <div class="detail">
        <span class="label">ID:</span> ${category.id}
    </div>
    
    <div class="detail">
        <span class="label">Nom:</span> ${category.name}
    </div>
    
    <div class="detail">
        <span class="label">Description:</span> ${category.description}
    </div>
    
    <div class="detail">
        <span class="label">Nombre de produits:</span> ${category.products.size()}
    </div>
    
    <div style="margin-top: 20px;">
        <a href="${pageContext.request.contextPath}/categories/${category.id}/edit" class="btn btn-primary">Modifier</a>
        <a href="${pageContext.request.contextPath}/categories" class="btn btn-secondary">Retour à la liste</a>
    </div>
    
    <c:if test="${not empty category.products}">
        <h2>Produits dans cette catégorie</h2>
        <table>
            <thead>
                <tr>
                    <th>Nom</th>
                    <th>Prix</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="product" items="${category.products}">
                    <tr>
                        <td>${product.name}</td>
                        <td>${product.price}€</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/products/${product.id}" class="btn btn-primary">Voir</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
</body>
</html>