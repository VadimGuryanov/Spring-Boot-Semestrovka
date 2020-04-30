package ru.itis.springboothomework.service.interfaces;

import ru.itis.springboothomework.models.Country;
import ru.itis.springboothomework.models.Sity;

import java.util.List;

public interface CountryService {

    List<Country> getCountries();
    Country getCountry(long id);
    Country getCountry(String name);
    List<Country> getPageCountry(Integer page, Integer size);
    List<Sity> getSites();
    List<Sity> getSites(long id);
}
