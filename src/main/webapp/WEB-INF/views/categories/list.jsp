<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Liste des Catégories</title>
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

    <h1>Liste des Catégories</h1>
    
    <a href="${pageContext.request.contextPath}/categories/new" class="btn btn-success">Nouvelle Catégorie</a>
    
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Nom</th>
                <th>Description</th>
                <th>Nb Produits</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="category" items="${categories}">
                <tr>
                    <td>${category.id}</td>
                    <td>${category.name}</td>
                    <td>${category.description}</td>
                    <td>${empty category.products ? 0 : category.products.size()}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/categories/${category.id}" class="btn btn-primary">Voir</a>
                        <a href="${pageContext.request.contextPath}/categories/${category.id}/edit" class="btn btn-primary">Modifier</a>
                        <form style="display:inline" method="post" action="${pageContext.request.contextPath}/categories/${category.id}/delete">
                            <button type="submit" class="btn btn-danger" onclick="return confirm('Êtes-vous sûr de vouloir supprimer cette catégorie?')">Supprimer</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>