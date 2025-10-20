package com.example.controller;

import com.example.dao.IDao;
import com.example.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    @Qualifier("categoryDaoImpl")
    private IDao<Category> categoryDao;

    @GetMapping
    public String listCategories(Model model) {
        List<Category> categories = categoryDao.findAll();
        model.addAttribute("categories", categories);
        return "categories/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("category", new Category());
        return "categories/form";
    }

    @PostMapping
    public String createCategory(@ModelAttribute Category category) {
        categoryDao.create(category);
        return "redirect:/categories";
    }

    @GetMapping("/{id}")
    public String viewCategory(@PathVariable int id, Model model) {
        Category category = categoryDao.findById(id);
        model.addAttribute("category", category);
        return "categories/view";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable int id, Model model) {
        Category category = categoryDao.findById(id);
        model.addAttribute("category", category);
        return "categories/form";
    }

    @PostMapping("/{id}")
    public String updateCategory(@PathVariable int id, @ModelAttribute Category category) {
        category.setId(id);
        categoryDao.update(category);
        return "redirect:/categories";
    }

    @PostMapping("/{id}/delete")
    public String deleteCategory(@PathVariable int id) {
        Category category = categoryDao.findById(id);
        if (category != null) {
            categoryDao.delete(category);
        }
        return "redirect:/categories";
    }
}