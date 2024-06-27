package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.yearup.data.CategoryDao;
import org.yearup.data.ProductDao;
import org.yearup.models.Category;
import org.yearup.models.Product;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/categories")
public class CategoriesController
{
    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private ProductDao productDao;

    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<Category>> getAll()
    {
        return ResponseEntity.ok(categoryDao.getAllCategories());
    }

    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<Category> getById(@PathVariable int id)
    {
        return ResponseEntity.ok(categoryDao.getById(id));
    }

    @GetMapping("{categoryId}/products")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<Product>> getProductsById(@PathVariable int categoryId)
    {
        return ResponseEntity.ok(productDao.listByCategoryId(categoryId));
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Category> addCategory(@RequestBody Category category)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryDao.create(category));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> updateCategory(@PathVariable int id, @RequestBody Category category)
    {
        categoryDao.update(id, category);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteCategory(@PathVariable int id)
    {
        categoryDao.delete(id);
        return ResponseEntity.noContent().build();
    }
}
