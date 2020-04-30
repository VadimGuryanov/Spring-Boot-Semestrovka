package ru.itis.springboothomework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.itis.springboothomework.aspects.ExceptionAnnotation;
import ru.itis.springboothomework.models.Country;
import ru.itis.springboothomework.models.Sity;
import ru.itis.springboothomework.models.User;
import ru.itis.springboothomework.repository.LocationRepository;
import ru.itis.springboothomework.service.interfaces.CountryService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CountriesServiceImpl implements CountryService {

    @Autowired
    private LocationRepository repository;

    @Override
    @ExceptionAnnotation
    public List<Country> getCountries() {
        return repository.findAll();
    }

    @Override
    @ExceptionAnnotation
    public Country getCountry(long id){
        Optional<Country> optional = repository.findById(id);
        return optional.get();
    }

    @ExceptionAnnotation
    @Override
    public Country getCountry(String name) {
//        repository.findCountryByName(name).getSities().forEach(System.out::println);
        return repository.findCountryByName(name);
    }

    @Override
    @ExceptionAnnotation
    public List<Country> getPageCountry(Integer page, Integer size) {
        PageRequest request = PageRequest.of(page, size);
        Page<Country> pageResult = repository.findAll(request);
        return pageResult.getContent();
    }

    @Override
    @ExceptionAnnotation
    public List<Sity> getSites() {
        List<Sity> sites = new ArrayList<>();
        List<List<Sity>> lists = repository.findAll().stream()
                .map(Country::getSities)
                .collect(Collectors.toList());
        for (List<Sity> listSity: lists) {
            sites.addAll(listSity);
        }
        return sites;
    }

    @Override
    @ExceptionAnnotation
    public List<Sity> getSites(long id) {
        return getCountry(id).getSities();
    }
}
