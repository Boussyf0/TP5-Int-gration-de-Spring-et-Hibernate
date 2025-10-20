package com.example.controller;

import com.example.dao.IDao;
import com.example.entities.Category;
import com.example.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    @Qualifier("productDaoImpl")
    private IDao<Product> productDao;

    @Autowired
    @Qualifier("categoryDaoImpl")
    private IDao<Category> categoryDao;

    @GetMapping
    public String listProducts(Model model) {
        List<Product> products = productDao.findAll();
        model.addAttribute("products", products);
        return "products/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryDao.findAll());
        return "products/form";
    }

    @PostMapping
    public String createProduct(@ModelAttribute Product product) {
        productDao.create(product);
        return "redirect:/products";
    }

    @GetMapping("/{id}")
    public String viewProduct(@PathVariable int id, Model model) {
        Product product = productDao.findById(id);
        model.addAttribute("product", product);
        return "products/view";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable int id, Model model) {
        Product product = productDao.findById(id);
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryDao.findAll());
        return "products/form";
    }

    @PostMapping("/{id}")
    public String updateProduct(@PathVariable int id, @ModelAttribute Product product) {
        product.setId(id);
        productDao.update(product);
        return "redirect:/products";
    }

    @PostMapping("/{id}/delete")
    public String deleteProduct(@PathVariable int id) {
        Product product = productDao.findById(id);
        if (product != null) {
            productDao.delete(product);
        }
        return "redirect:/products";
    }
}