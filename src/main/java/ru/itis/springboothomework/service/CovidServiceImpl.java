package ru.itis.springboothomework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.springboothomework.models.CovidData;
import ru.itis.springboothomework.repository.CovidRepository;
import ru.itis.springboothomework.service.interfaces.CovidService;

@Service
public class CovidServiceImpl implements CovidService {

    @Autowired
    private CovidRepository covidRepository;

    @Override
    public CovidData get() {
        return covidRepository.getCovidData();
    }
}
