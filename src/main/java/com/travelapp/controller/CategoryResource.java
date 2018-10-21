package com.travelapp.controller;

import com.travelapp.controller.util.HeaderUtil;
import com.travelapp.exception.BadRequestException;
import com.travelapp.exception.ResourceNotFoundException;
import com.travelapp.model.Category;
import com.travelapp.model.Provider;
import com.travelapp.service.CategoryService;
import com.travelapp.service.ProviderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CategoryResource {
    private final Logger log = LoggerFactory.getLogger(ProviderResource.class);

    private static final String ENTITY_NAME = "category";

    private final CategoryService categoryService;
    @Autowired
    public CategoryResource(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @PostMapping("/category")
    public ResponseEntity<Category> createCategory(@Valid @RequestBody Category category) throws URISyntaxException {
        log.debug("REST request to save Category : {}", category);
        if (category.getId() != null) {
            throw new BadRequestException("A new provider cannot already have an ID");
        }
        Category result = categoryService.save(category);
        return ResponseEntity.created(new URI("/api/category/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }


    @PutMapping("/category")
    public ResponseEntity<Category> updateCategory(@Valid @RequestBody Category category) throws URISyntaxException {
        log.debug("REST request to update Category : {}", category);
        if (category.getId() == null) {
            throw new BadRequestException("Invalid id");
        }
        Category result = categoryService.save(category);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, category.getId().toString()))
                .body(result);
    }


    @GetMapping("/category")
    public List<Category> getAllCategory() {
        log.debug("REST request to get all Category");
        return categoryService.findAll();
    }

    @GetMapping("/category/{id}")

    public ResponseEntity<Category> getCategory(@PathVariable Long id) {
        log.debug("REST request to get Category : {}", id);
        Optional<Category> category = categoryService.findOne(id);
        if (category==null)
            throw new ResourceNotFoundException(ENTITY_NAME,"id",id);
        return ResponseEntity.ok(category.get());
    }


    @DeleteMapping("/category/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        log.debug("REST request to delete category : {}", id);
        categoryService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
