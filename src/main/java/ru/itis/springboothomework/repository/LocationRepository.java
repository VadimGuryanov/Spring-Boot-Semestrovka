package ru.itis.springboothomework.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.springboothomework.models.Country;
import ru.itis.springboothomework.models.Sity;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Country, Long> {

    Country findCountryByName(String name);

}
