<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Liste des Produits</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        .nav { margin-bottom: 30px; }
        .nav a { margin-right: 20px; text-decoration: none; color: #007bff; }
        .nav a:hover { text-decoration: underline; }
        table { border-collapse: collapse; width: 100%; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        .btn { padding: 5px 10px; margin: 2px; text-decoration: none; border-radius: 3px; }
        .btn-primary { background-color: #007bff; color: white; }
        .btn-danger { background-color: #dc3545; color: white; }
        .btn-success { background-color: #28a745; color: white; }
    </style>
</head>
<body>
    <div class="nav">
        <a href="${pageContext.request.contextPath}/">Accueil</a>
        <a href="${pageContext.request.contextPath}/products">Produits</a>
        <a href="${pageContext.request.contextPath}/categories">Catégories</a>
    </div>

    <h1>Liste des Produits</h1>
    
    <a href="${pageContext.request.contextPath}/products/new" class="btn btn-success">Nouveau Produit</a>
    
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Nom</th>
                <th>Prix</th>
                <th>Catégorie</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="product" items="${products}">
                <tr>
                    <td>${product.id}</td>
                    <td>${product.name}</td>
                    <td>${product.price}€</td>
                    <td>${product.category != null ? product.category.name : 'Aucune'}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/products/${product.id}" class="btn btn-primary">Voir</a>
                        <a href="${pageContext.request.contextPath}/products/${product.id}/edit" class="btn btn-primary">Modifier</a>
                        <form style="display:inline" method="post" action="${pageContext.request.contextPath}/products/${product.id}/delete">
                            <button type="submit" class="btn btn-danger" onclick="return confirm('Êtes-vous sûr de vouloir supprimer ce produit?')">Supprimer</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>