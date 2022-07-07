package tech.surfer.examserver.service;

import tech.surfer.examserver.entity.exam.Category;

import java.util.Set;

public interface CategoryService {

    Category add(Category category);
    Category updateCategory(Category category);
    Set<Category> getCategories();
    Category getCategory(Long categoryId);
    void delete(Long categoryId);
}
