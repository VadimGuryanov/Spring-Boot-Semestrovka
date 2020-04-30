package ru.itis.springboothomework.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.itis.springboothomework.aspects.ExceptionAnnotation;
import ru.itis.springboothomework.dto.SummaryDto;
import ru.itis.springboothomework.models.Category;
import ru.itis.springboothomework.models.Summary;
import ru.itis.springboothomework.repository.SummaryRepository;
import ru.itis.springboothomework.service.interfaces.CategoryService;
import ru.itis.springboothomework.service.interfaces.SummaryService;
import ru.itis.springboothomework.service.interfaces.UserService;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class SummaryServiceImpl implements SummaryService {

    @Autowired
    @Qualifier("summaryRepository")
    private SummaryRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    @Qualifier("categoryService")
    private CategoryService categoryService;

    @Override
    @ExceptionAnnotation
    public long save(SummaryDto summaryDto) {
        Summary summary = new Summary();

        Set<Category> categories = getCategories(
                summaryDto.getSummaryCategories(),
                summaryDto.getCustomCategory());

//        if (summaryDto.getCustomCategory() != null) {
//            Category category = new Category();
//            category.setName(summaryDto.getCustomCategory());
//            categoryService.save(category);
//        }

//        Set<Category> categoriesForUser = new HashSet<>();
//        for (Category category: categoryService.getCategories()) {
//            for (Category c: categories) {
//                System.out.println(category.getName());
//                System.out.println(c.getName());
//                System.out.println("/");
//                if ((category.getName().equals(c.getName()))) {
//                    categoriesForUser.add(c);
//                }
//            }
//        }
        summary.setTitle(summaryDto.getTitle());
        summary.setCountry(summaryDto.getCountry());
        summary.setSity(summaryDto.getSity());
        summary.setCost(summaryDto.getCost() + " " + summaryDto.getCost_val());
        summary.setWork(summaryDto.getWork());
        summary.setExperience(summaryDto.getExperience());
        summary.setDescription(summaryDto.getDescription());
        summary.setSummaryCategories(categories);
        summary.setUser(userService.getCurrentUser());
        summary = repository.save(summary);
        return summary.getId();
    }

    @Override
    @ExceptionAnnotation
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    @ExceptionAnnotation
    public void delete(Summary summary) {
        categoryService.deleteAll(summary.getSummaryCategories());
        repository.delete(summary);
    }

    @Override
    @ExceptionAnnotation
    public long update(SummaryDto summaryDto, Summary summary) {
        summary.setTitle(summaryDto.getTitle());
        summary.setCountry(summaryDto.getCountry());
        summary.setSity(summaryDto.getSity());
        if (summary.getCost() != null) {
            summary.setCost(summaryDto.getCost() + " " + summaryDto.getCost_val());
        }
        summary.setWork(summaryDto.getWork());
        summary.setExperience(summaryDto.getExperience());
        summary.setDescription(summaryDto.getDescription());
        summary.setSummaryCategories(getCategories(
                summaryDto.getSummaryCategories(),
                summaryDto.getCustomCategory())
        );
        summary.setUser(userService.getCurrentUser());
        System.out.println(summary.getId());
        delete(summary);
        summary = repository.save(summary);
        return summary.getId();
    }

    @Override
    @ExceptionAnnotation
    public Summary getById(long id) throws NotFoundException {
        Optional<Summary> summary = repository.findById(id);
        if (summary.isPresent()) {
            return summary.get();
        }
        throw new NotFoundException("");
    }

    @Override
    @ExceptionAnnotation
    public Summary getByTitle(String title) {
        Optional<Summary> summary = repository.findSummaryByTitle(title);
        return summary.orElse(null);
    }

    @Override
    @ExceptionAnnotation
    public Optional<Summary> getByCategory(String category) {
        return Optional.empty();
    }

    private Set<Category> getCategories(List<String> categoriesString, String category) {
        Set<Category> categories = new HashSet<>();
        if (categoriesString != null) {
            if (category != null && !category.equals("")) categoriesString.add(category);
            for (String s : categoriesString) {
                Category c = new Category();
                c.setName(s);
                categories.add(c);
            }
        } else {
            Category c = new Category();
            c.setName(category);
            categories.add(c);
        }
        categories.forEach(s -> System.out.println(s.getName()));
        return categories;
    }
}
