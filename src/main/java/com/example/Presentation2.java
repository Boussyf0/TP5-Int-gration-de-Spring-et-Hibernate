package com.example;

import com.example.dao.IDao;
import com.example.entities.Category;
import com.example.entities.Product;
import com.example.util.HibernateConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Presentation2 {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);

        IDao<Product> productDao = context.getBean("productDaoImpl", IDao.class);
        IDao<Category> categoryDao = context.getBean("categoryDaoImpl", IDao.class);

        Category category = new Category("Electronics", "Electronic devices");
        categoryDao.create(category);

        Product product = new Product();
        product.setName("Laptop Dell");
        product.setPrice(1299.99);
        product.setCategory(category);

        productDao.create(product);

        System.out.println("Catégorie sauvegardée : " + category.getName());
        System.out.println("Produit sauvegardé : " + product.getName() + " - Prix: " + product.getPrice() + "€");
        System.out.println("Catégorie du produit : " + product.getCategory().getName());
    }
}
