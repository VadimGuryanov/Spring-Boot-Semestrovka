package ru.itis.springboothomework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.springboothomework.models.Summary;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository("summaryRepository")
public interface SummaryRepository extends JpaRepository<Summary, Long> {

    Optional<Summary> findSummaryByTitle(String title);
}
