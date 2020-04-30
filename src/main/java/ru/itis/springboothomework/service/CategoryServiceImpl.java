package ru.itis.springboothomework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.itis.springboothomework.aspects.ExceptionAnnotation;
import ru.itis.springboothomework.models.Category;
import ru.itis.springboothomework.models.Summary;
import ru.itis.springboothomework.repository.CategoryRepository;
import ru.itis.springboothomework.service.interfaces.CategoryService;

import java.util.*;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    @Qualifier("categoryRepository")
    private CategoryRepository repository;

    @Override
    @ExceptionAnnotation
    public Category getById(long id) {
        Optional<Category> category = repository.findById(id);
        return category.orElse(null);
    }

    @Override
    @ExceptionAnnotation
    public List<Category> getCategories() {
        return repository.findAll();
    }

    @Override
    @ExceptionAnnotation
    public void save(Category category) {
        repository.save(category);
    }

    @Override
    @ExceptionAnnotation
    public void delete(Category category) {
        repository.delete(category);
    }

    @Override
    @ExceptionAnnotation
    public void saveList(Collection<Category> categories) {
        repository.saveAll(categories);
    }

    @Override
    @ExceptionAnnotation
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Override
    @ExceptionAnnotation
    public void saveSummary(Summary summary, Set<Category> c) {
        Set<Summary> summaries;
        for (Category category: c) {
            summaries = category.getSummaries();
            summaries.add(summary);
            category.setSummaries(summaries);
            repository.delete(category);
            repository.save(category);
        }
    }

    @Override
    @ExceptionAnnotation
    public void deleteAll(Set<Category> summaryCategories) {
        repository.deleteAll(summaryCategories);
    }
}
