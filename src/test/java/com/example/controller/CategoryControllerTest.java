package com.example.controller;

import com.example.config.TestConfig;
import com.example.dao.IDao;
import com.example.entities.Category;
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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class CategoryControllerTest {

    private MockMvc mockMvc;

    @Mock
    private IDao<Category> categoryDao;

    @InjectMocks
    private CategoryController categoryController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController)
                .setViewResolvers(viewResolver)
                .build();
    }

    @Test
    public void testListCategories() throws Exception {
        List<Category> categories = Arrays.asList(
            new Category("Category 1", "Description 1"),
            new Category("Category 2", "Description 2")
        );
        
        when(categoryDao.findAll()).thenReturn(categories);
        
        mockMvc.perform(get("/categories"))
                .andExpect(status().isOk())
                .andExpect(view().name("categories/list"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(model().attribute("categories", categories));
        
        verify(categoryDao).findAll();
    }

    @Test
    public void testShowCreateForm() throws Exception {
        mockMvc.perform(get("/categories/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("categories/form"))
                .andExpect(model().attributeExists("category"));
    }

    @Test
    public void testCreateCategory() throws Exception {
        when(categoryDao.create(any(Category.class))).thenReturn(true);
        
        mockMvc.perform(post("/categories")
                .param("name", "New Category")
                .param("description", "New Description"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/categories"));
        
        verify(categoryDao).create(any(Category.class));
    }

    @Test
    public void testViewCategory() throws Exception {
        Category category = new Category("Test Category", "Test Description");
        category.setId(1);
        
        when(categoryDao.findById(1)).thenReturn(category);
        
        mockMvc.perform(get("/categories/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("categories/view"))
                .andExpect(model().attributeExists("category"))
                .andExpect(model().attribute("category", category));
        
        verify(categoryDao).findById(1);
    }

    @Test
    public void testShowEditForm() throws Exception {
        Category category = new Category("Test Category", "Test Description");
        category.setId(1);
        
        when(categoryDao.findById(1)).thenReturn(category);
        
        mockMvc.perform(get("/categories/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("categories/form"))
                .andExpect(model().attributeExists("category"));
        
        verify(categoryDao).findById(1);
    }

    @Test
    public void testUpdateCategory() throws Exception {
        when(categoryDao.update(any(Category.class))).thenReturn(true);
        
        mockMvc.perform(post("/categories/1")
                .param("name", "Updated Category")
                .param("description", "Updated Description"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/categories"));
        
        verify(categoryDao).update(any(Category.class));
    }

    @Test
    public void testDeleteCategory() throws Exception {
        Category category = new Category("Test Category", "Test Description");
        category.setId(1);
        
        when(categoryDao.findById(1)).thenReturn(category);
        when(categoryDao.delete(category)).thenReturn(true);
        
        mockMvc.perform(post("/categories/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/categories"));
        
        verify(categoryDao).findById(1);
        verify(categoryDao).delete(category);
    }
}