<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${product.id == 0 ? 'Nouveau' : 'Modifier'} Produit</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        .nav { margin-bottom: 30px; }
        .nav a { margin-right: 20px; text-decoration: none; color: #007bff; }
        .nav a:hover { text-decoration: underline; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; }
        input, select { width: 300px; padding: 8px; border: 1px solid #ddd; border-radius: 4px; }
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

    <h1>${product.id == 0 ? 'Nouveau' : 'Modifier'} Produit</h1>
    
    <form method="post" action="${product.id == 0 ? pageContext.request.contextPath.concat('/products') : pageContext.request.contextPath.concat('/products/').concat(product.id)}">
        <div class="form-group">
            <label for="name">Nom:</label>
            <input type="text" id="name" name="name" value="${product.name}" required>
        </div>
        
        <div class="form-group">
            <label for="price">Prix:</label>
            <input type="number" step="0.01" id="price" name="price" value="${product.price}" required>
        </div>
        
        <div class="form-group">
            <label for="category">Catégorie:</label>
            <select id="category" name="category.id">
                <option value="">Sélectionner une catégorie</option>
                <c:forEach var="category" items="${categories}">
                    <option value="${category.id}" ${product.category != null && product.category.id == category.id ? 'selected' : ''}>
                        ${category.name}
                    </option>
                </c:forEach>
            </select>
        </div>
        
        <button type="submit" class="btn btn-primary">Enregistrer</button>
        <a href="${pageContext.request.contextPath}/products" class="btn btn-secondary">Annuler</a>
    </form>
</body>
</html>