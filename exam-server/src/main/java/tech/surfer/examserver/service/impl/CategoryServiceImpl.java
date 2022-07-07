package tech.surfer.examserver.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.surfer.examserver.entity.exam.Category;
import tech.surfer.examserver.repo.CategoryRepository;
import tech.surfer.examserver.service.CategoryService;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    @NonNull
    private CategoryRepository categoryRepository;

    @Override
    public Category add(Category category) {
        return this.categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        return this.categoryRepository.save(category);
    }

    @Override
    public Set<Category> getCategories() {
        return new LinkedHashSet<>(this.categoryRepository.findAll());
    }

    @Override
    public Category getCategory(Long categoryId) {
        return this.categoryRepository.findById(categoryId).get();
    }

    @Override
    public void delete(Long categoryId) {
        this.categoryRepository.deleteById(categoryId);
    }
}
