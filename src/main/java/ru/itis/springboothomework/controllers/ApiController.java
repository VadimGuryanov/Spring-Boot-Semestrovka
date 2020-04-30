package ru.itis.springboothomework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.itis.springboothomework.models.CovidData;
import ru.itis.springboothomework.service.interfaces.CovidService;

@Controller
public class ApiController {

    @Autowired
    private CovidService covidService;

    @RequestMapping(value = "/covid", method = RequestMethod.GET)
    private String covid(ModelMap map) {
        CovidData covidData = covidService.get();
        map.put("covid", covidData);
        return "covid";
    }

}
