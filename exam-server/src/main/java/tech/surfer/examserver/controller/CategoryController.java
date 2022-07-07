package tech.surfer.examserver.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.surfer.examserver.entity.exam.Category;
import tech.surfer.examserver.service.CategoryService;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
public class CategoryController {

    @NonNull
    private CategoryService categoryService;

    /**
     * add Category
     *
     */
    @PostMapping("/")
    public ResponseEntity addCategory(@RequestBody Category category) {
        Category category1 = this.categoryService.add(category);
        return ResponseEntity.ok(category1);
    }

    /**
     * Get Category
     */
    @GetMapping("/{categoryId}")
    public ResponseEntity getCategory(@PathVariable("categoryId") Long categoryId) {
        return ResponseEntity.ok(this.categoryService.getCategory(categoryId));
    }

    /**
     * get all categories
     */
    @GetMapping("/")
    public ResponseEntity getCategories() {
        return ResponseEntity.ok(this.categoryService.getCategories());
    }

    /**
     * update category
     */
    @PutMapping("/")
    public Category updateCategory(@RequestBody Category category) {
        return this.categoryService.updateCategory(category);
    }

    /**
     * delete category
     */
    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable("categoryId") Long categoryId) {
        this.categoryService.delete(categoryId);
    }
}
