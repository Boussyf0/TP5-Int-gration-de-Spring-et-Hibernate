package com.example.controller;

import com.example.config.TestConfig;
import com.example.dao.IDao;
import com.example.entities.Category;
import com.example.entities.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class ProductControllerTest {

    private MockMvc mockMvc;

    @Mock
    private IDao<Product> productDao;

    @Mock
    private IDao<Category> categoryDao;

    @InjectMocks
    private ProductController productController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        
        mockMvc = MockMvcBuilders.standaloneSetup(productController)
                .setViewResolvers(viewResolver)
                .build();
    }

    @Test
    public void testListProducts() throws Exception {
        List<Product> products = Arrays.asList(
            new Product("Product 1", 10.0),
            new Product("Product 2", 20.0)
        );
        
        when(productDao.findAll()).thenReturn(products);
        
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(view().name("products/list"))
                .andExpect(model().attributeExists("products"))
                .andExpect(model().attribute("products", products));
        
        verify(productDao).findAll();
    }

    @Test
    public void testShowCreateForm() throws Exception {
        List<Category> categories = Arrays.asList(
            new Category("Category 1", "Description 1"),
            new Category("Category 2", "Description 2")
        );
        
        when(categoryDao.findAll()).thenReturn(categories);
        
        mockMvc.perform(get("/products/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("products/form"))
                .andExpect(model().attributeExists("product"))
                .andExpect(model().attributeExists("categories"));
        
        verify(categoryDao).findAll();
    }

    @Test
    public void testCreateProduct() throws Exception {
        when(productDao.create(any(Product.class))).thenReturn(true);
        
        mockMvc.perform(post("/products")
                .param("name", "New Product")
                .param("price", "25.99"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/products"));
        
        verify(productDao).create(any(Product.class));
    }

    @Test
    public void testViewProduct() throws Exception {
        Product product = new Product("Test Product", 15.0);
        product.setId(1);
        
        when(productDao.findById(1)).thenReturn(product);
        
        mockMvc.perform(get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("products/view"))
                .andExpect(model().attributeExists("product"))
                .andExpect(model().attribute("product", product));
        
        verify(productDao).findById(1);
    }

    @Test
    public void testShowEditForm() throws Exception {
        Product product = new Product("Test Product", 15.0);
        product.setId(1);
        
        List<Category> categories = Arrays.asList(
            new Category("Category 1", "Description 1")
        );
        
        when(productDao.findById(1)).thenReturn(product);
        when(categoryDao.findAll()).thenReturn(categories);
        
        mockMvc.perform(get("/products/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("products/form"))
                .andExpect(model().attributeExists("product"))
                .andExpect(model().attributeExists("categories"));
        
        verify(productDao).findById(1);
        verify(categoryDao).findAll();
    }

    @Test
    public void testUpdateProduct() throws Exception {
        when(productDao.update(any(Product.class))).thenReturn(true);
        
        mockMvc.perform(post("/products/1")
                .param("name", "Updated Product")
                .param("price", "35.99"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/products"));
        
        verify(productDao).update(any(Product.class));
    }

    @Test
    public void testDeleteProduct() throws Exception {
        Product product = new Product("Test Product", 15.0);
        product.setId(1);
        
        when(productDao.findById(1)).thenReturn(product);
        when(productDao.delete(product)).thenReturn(true);
        
        mockMvc.perform(post("/products/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/products"));
        
        verify(productDao).findById(1);
        verify(productDao).delete(product);
    }
}