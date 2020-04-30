package ru.itis.springboothomework.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.springboothomework.models.Category;
import ru.itis.springboothomework.models.Summary;

import java.util.List;

@Repository
public interface SearchRepository extends JpaRepository<Summary, Long> {

    List<Summary> findSummaryByTitleLike(String title);

    List<Summary> findAllByCountryAndSity(String country, String sity);

    List<Summary> findAllByCountry(String country);

    List<Summary> findAllBySity(String sity);

//    Page<Summary> findAllBySummaryCategories(List<Category> categories, PageRequest request);
}
