package ru.itis.springboothomework.service;

import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.itis.springboothomework.aspects.ExceptionAnnotation;
import ru.itis.springboothomework.models.Category;
import ru.itis.springboothomework.models.Summary;
import ru.itis.springboothomework.repository.CategoryRepository;
import ru.itis.springboothomework.repository.SearchRepository;
import ru.itis.springboothomework.service.interfaces.SearchService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements SearchService {

    private final int SIZE = 5;

    @Autowired
    @Qualifier("categoryRepository")
    private CategoryRepository categoryRepository;

    @Autowired
    private SearchRepository searchRepository;

    @Override
    @ExceptionAnnotation
    public List<Summary> findSummary(String query) {
        query = query.toLowerCase();
        List<Set<Summary>> byCategory = categoryRepository.getByNameLike("%"+ query + "%")
                .stream()
                .map(Category::getSummaries)
                .collect(Collectors.toList());
        List<Summary> byTitle = searchRepository.findSummaryByTitleLike("%"+ query + "%");
        Set<Long> ids = byTitle.stream()
                .map(Summary::getId)
                .collect(Collectors.toSet());
        for (Set<Summary> summaries: byCategory) {
            ids.addAll(summaries.stream().map(Summary::getId).collect(Collectors.toSet()));
        }
        return searchRepository.findAllById(ids);
    }

    @Override
    @ExceptionAnnotation
    public List<Summary> findSummary(Integer page) {
        PageRequest request = PageRequest.of(page, SIZE);
        return searchRepository.findAll(request).getContent();
    }

    @Override
    @ExceptionAnnotation
    public List<Summary> findByCountry(String country) {
        country = country.toLowerCase();
        return searchRepository.findAllByCountry(country);
    }

    @Override
    @ExceptionAnnotation
    public List<Summary> findByCountryAndSity(String country, String sity) {
        country = country.toLowerCase();
        sity = sity.toLowerCase();
        return searchRepository.findAllByCountryAndSity(country, sity);
    }

    @Override
    @ExceptionAnnotation
    public List<Summary> findBySity(String sity) {
        sity = sity.toLowerCase();
        return searchRepository.findAllBySity(sity);
    }
//
//    @Override
//    public List<Summary> findByCategories(List<Category> categories, Integer page) {
//        PageRequest request = PageRequest.of(page, SIZE);
//        return searchRepository.findAllBySummaryCategories(categories, request).getContent();
//    }

    @Override
    @ExceptionAnnotation
    public List<Summary> findByQueryAndCountry(String query, String country) {
        country = country.toLowerCase();
        query = query.toLowerCase();
        List<Summary> summariesByCountry = searchRepository.findAllByCountry(country);
        return getList(summariesByCountry, query);
    }

    @Override
    @ExceptionAnnotation
    public List<Summary> findByQueryAndSity(String query, String sity) {
        query = query.toLowerCase();
        sity = sity.toLowerCase();
        List<Summary> summariesBySity = searchRepository.findAllBySity(sity);
        return getList(summariesBySity, query);
    }

    @Override
    @ExceptionAnnotation
    public List<Summary> findByQueryAndSityAndCountry(String query, String country, String sity) {
        country = country.toLowerCase();
        sity = sity.toLowerCase();
        query = query.toLowerCase();
        List<Summary> summariesBy = findByCountryAndSity(country, sity);
        return getList(summariesBy, query);
    }

    private List<Summary> getList(List<Summary> summariesBy, String query) {
        Set<Long> ids = new HashSet<>();
        for (Summary summary: summariesBy) {
            if (summary.getTitle().contains(query)) {
                ids.add(summary.getId());
            }
        }
        for (Summary summary: summariesBy) {
            for (Category category: summary.getSummaryCategories()) {
                if (category.getName().contains(query)) {
                    System.out.println(category.getName());
                    ids.add(summary.getId());
                }
            }
        }
        return searchRepository.findAllById(ids);
    }

}
