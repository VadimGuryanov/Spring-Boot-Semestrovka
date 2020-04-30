package ru.itis.springboothomework.service.interfaces;

import ru.itis.springboothomework.aspects.ExceptionAnnotation;
import ru.itis.springboothomework.models.Category;
import ru.itis.springboothomework.models.Summary;

import java.util.List;

public interface SearchService {

    List<Summary> findSummary(String query);

    List<Summary> findSummary(Integer page);

    @ExceptionAnnotation
    List<Summary> findByCountry(String country);

    @ExceptionAnnotation
    List<Summary> findByCountryAndSity(String country, String sity);

    @ExceptionAnnotation
    List<Summary> findBySity(String sity);

    @ExceptionAnnotation
    List<Summary> findByQueryAndCountry(String query, String country);

    @ExceptionAnnotation
    List<Summary> findByQueryAndSity(String query, String sity);

    @ExceptionAnnotation
    List<Summary> findByQueryAndSityAndCountry(String query, String country, String sity);
}
