package ru.itis.springboothomework.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.springboothomework.models.Category;
import ru.itis.springboothomework.models.Summary;

import java.util.List;

@Repository("categoryRepository")
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> getByNameLike(String name);

    Page<Category> getByNameLike(String name, PageRequest request);


}
