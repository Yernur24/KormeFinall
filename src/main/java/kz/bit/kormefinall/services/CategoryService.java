package kz.bit.kormefinall.services;

import kz.bit.kormefinall.models.Category;
import kz.bit.kormefinall.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    public List<Category> allCategories() {
        return categoryRepository.findAll();
    }
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }
}