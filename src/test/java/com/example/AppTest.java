package com.example;

import com.example.config.TestConfig;
import com.example.dao.IDao;
import com.example.entities.Category;
import com.example.entities.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Transactional
public class AppTest {

    @Autowired
    @Qualifier("productDaoImpl")
    private IDao<Product> productDao;

    @Autowired
    @Qualifier("categoryDaoImpl")
    private IDao<Category> categoryDao;

    @Test
    public void testProductCrud() {
        Product product = new Product("Test Product", 99.99);
        
        boolean created = productDao.create(product);
        assertTrue("Product should be created", created);
        assertNotEquals("Product ID should be generated", 0, product.getId());

        Product foundProduct = productDao.findById(product.getId());
        assertNotNull("Product should be found", foundProduct);
        assertEquals("Product name should match", "Test Product", foundProduct.getName());
        assertEquals("Product price should match", 99.99, foundProduct.getPrice(), 0.01);

        foundProduct.setName("Updated Product");
        foundProduct.setPrice(149.99);
        boolean updated = productDao.update(foundProduct);
        assertTrue("Product should be updated", updated);

        Product updatedProduct = productDao.findById(product.getId());
        assertEquals("Updated name should match", "Updated Product", updatedProduct.getName());
        assertEquals("Updated price should match", 149.99, updatedProduct.getPrice(), 0.01);

        boolean deleted = productDao.delete(updatedProduct);
        assertTrue("Product should be deleted", deleted);
    }

    @Test
    public void testCategoryCrud() {
        Category category = new Category("Test Category", "Test Description");
        
        boolean created = categoryDao.create(category);
        assertTrue("Category should be created", created);
        assertNotEquals("Category ID should be generated", 0, category.getId());

        Category foundCategory = categoryDao.findById(category.getId());
        assertNotNull("Category should be found", foundCategory);
        assertEquals("Category name should match", "Test Category", foundCategory.getName());
        assertEquals("Category description should match", "Test Description", foundCategory.getDescription());

        foundCategory.setName("Updated Category");
        foundCategory.setDescription("Updated Description");
        boolean updated = categoryDao.update(foundCategory);
        assertTrue("Category should be updated", updated);

        Category updatedCategory = categoryDao.findById(category.getId());
        assertEquals("Updated name should match", "Updated Category", updatedCategory.getName());
        assertEquals("Updated description should match", "Updated Description", updatedCategory.getDescription());

        boolean deleted = categoryDao.delete(updatedCategory);
        assertTrue("Category should be deleted", deleted);
    }

    @Test
    public void testProductCategoryRelationship() {
        Category category = new Category("Electronics", "Electronic products");
        categoryDao.create(category);

        Product product = new Product("Laptop", 1299.99, category);
        category.addProduct(product);
        productDao.create(product);

        Product foundProduct = productDao.findById(product.getId());
        assertNotNull("Product should be found", foundProduct);
        assertNotNull("Product should have a category", foundProduct.getCategory());
        assertEquals("Category name should match", "Electronics", foundProduct.getCategory().getName());

        Category foundCategory = categoryDao.findById(category.getId());
        assertNotNull("Category should be found", foundCategory);
        
        // Force initialization of lazy collection in test
        org.hibernate.Hibernate.initialize(foundCategory.getProducts());
        assertEquals("Category should have one product", 1, foundCategory.getProducts().size());
        assertEquals("Product in category should match", "Laptop", foundCategory.getProducts().get(0).getName());
    }
}
