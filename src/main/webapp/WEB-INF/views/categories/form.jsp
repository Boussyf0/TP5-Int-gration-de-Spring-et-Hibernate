<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${category.id == 0 ? 'Nouvelle' : 'Modifier'} Catégorie</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        .nav { margin-bottom: 30px; }
        .nav a { margin-right: 20px; text-decoration: none; color: #007bff; }
        .nav a:hover { text-decoration: underline; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; }
        input, textarea { width: 300px; padding: 8px; border: 1px solid #ddd; border-radius: 4px; }
        textarea { height: 100px; resize: vertical; }
        .btn { padding: 10px 20px; margin: 5px; text-decoration: none; border-radius: 3px; border: none; cursor: pointer; }
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

    <h1>${category.id == 0 ? 'Nouvelle' : 'Modifier'} Catégorie</h1>
    
    <form method="post" action="${category.id == 0 ? pageContext.request.contextPath.concat('/categories') : pageContext.request.contextPath.concat('/categories/').concat(category.id)}">
        <div class="form-group">
            <label for="name">Nom:</label>
            <input type="text" id="name" name="name" value="${category.name}" required>
        </div>
        
        <div class="form-group">
            <label for="description">Description:</label>
            <textarea id="description" name="description">${category.description}</textarea>
        </div>
        
        <button type="submit" class="btn btn-primary">Enregistrer</button>
        <a href="${pageContext.request.contextPath}/categories" class="btn btn-secondary">Annuler</a>
    </form>
</body>
</html>