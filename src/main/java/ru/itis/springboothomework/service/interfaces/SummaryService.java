package ru.itis.springboothomework.service.interfaces;

import javassist.NotFoundException;
import ru.itis.springboothomework.dto.SummaryDto;
import ru.itis.springboothomework.models.Summary;

import java.util.Optional;

public interface SummaryService {

    long save(SummaryDto summaryDto);
    void delete(long id);

    void delete(Summary summary);

    long update(SummaryDto summaryDto, Summary summary);
    Summary getById(long id) throws NotFoundException;
    Summary getByTitle(String title);
    Optional<Summary> getByCategory(String category);

}
