package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.data.CategoryDao;
import org.yearup.data.ProductDao;
import org.yearup.models.Category;
import org.yearup.models.Product;

import java.util.List;

/*
The CategoriesController handles HTTP requests related to Category.
 */
@RestController
@RequestMapping("/categories") //Base Url
@CrossOrigin // Allows Cross-Origin request for Front-End
public class CategoriesController {

    //Injecting the Dao dependency
    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private ProductDao productDao;

    //Get All Categories
    @GetMapping
    public List<Category> getAll() {
        return categoryDao.getAllCategories();
    }

    //Getting category by Id
    @GetMapping("/{id}")
    public Category getById(@PathVariable int id) {
        Category category = categoryDao.getById(id);
        if (category == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
        }
        return category;
    }

    //Getting all Products with a specific category Id
    @GetMapping("{categoryId}/products")
    public List<Product> getProductsById(@PathVariable int categoryId) {
        return productDao.listByCategoryId(categoryId);
    }

    //Adding new Category (With Admin Role)
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')") //This Restricts to Admin Role only, no regular users allowed >:)
    @ResponseStatus(HttpStatus.CREATED) //This sets response status to 201 Created
    public Category addCategory(@RequestBody Category category) {
        return categoryDao.create(category);
    }

    //Updating Category (With Admin Role)
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCategory(@PathVariable int id, @RequestBody Category category) {
        categoryDao.update(id, category);
    }

    //Deleting the Category (With Admin Role)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable int id) {
        categoryDao.delete(id);
    }
}
