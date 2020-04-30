package ru.itis.springboothomework.service.interfaces;

import ru.itis.springboothomework.models.Category;
import ru.itis.springboothomework.models.Summary;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface CategoryService {

    Category getById(long id);
    List<Category> getCategories();
    void save(Category category);
    void delete(Category category);
    void saveList(Collection<Category> categories);
    void deleteById(long id);
    void saveSummary(Summary summary, Set<Category> c);
    void deleteAll(Set<Category> summaryCategories);
}
