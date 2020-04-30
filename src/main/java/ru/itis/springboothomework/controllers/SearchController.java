package ru.itis.springboothomework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import ru.itis.springboothomework.models.Summary;
import ru.itis.springboothomework.service.interfaces.CategoryService;
import ru.itis.springboothomework.service.interfaces.CountryService;
import ru.itis.springboothomework.service.interfaces.SearchService;
import ru.itis.springboothomework.service.interfaces.UserService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private SearchService searchService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;


    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String redirect() {
        return "redirect:" + MvcUriComponentsBuilder.fromMappingName("PC#profile").build();
    }

//    @RequestMapping(path = "/search", method = RequestMethod.GET)
//    public String search(ModelMap map) {
//        if (userService.getCurrentUser() != null) {
//            map.put("anon", "anon");
//        }
//        map.put("search", "s");
//        map.put("summaries", searchService.findSummary(0));
//        map.put("categories", categoryService.getCategories());
//        map.put("countries", countryService.getCountries());
//        return "search";
//    }
//
    @RequestMapping(path = "/search", method = RequestMethod.GET)
    public String search(@RequestParam(required = false) String query, ModelMap map) {
        if (userService.getCurrentUser() != null) {
            map.put("anon", "anon");
        }
        map.put("question", query);
        map.put("search", "s");
        if (query == null || query.equals("")) {
            map.put("summaries", searchService.findSummary(0));
        } else {
            map.put("summaries", searchService.findSummary(query));
        }
        map.put("categories", categoryService.getCategories());
        map.put("countries", countryService.getCountries());
        return "search";
    }

    @ResponseBody
    @RequestMapping(path = "/search", method = RequestMethod.POST)
    public String searchPost(@RequestParam String query, ModelMap map) {
        map.put("question", query);
        map.put("summaries", searchService.findSummary(query));
        map.put("categories", categoryService.getCategories());
        map.put("countries", countryService.getCountries());
//        map.put()
        return "search";
    }

    @ResponseBody
    @RequestMapping(path = "/getNewPage", method = RequestMethod.POST, headers = "Accept=application/json")
    private List<Summary> getNewPage(@RequestBody String page) {
        System.out.println(page);
        return  searchService.findSummary(Integer.valueOf(page.split("=")[1]));
    }

    @ResponseBody
    @RequestMapping(path = "/getNewPageQuery", method = RequestMethod.POST)
    private List<Summary> getNewPageQuery(String query) {
        System.out.println(query);
        return searchService.findSummary(query);
    }

    @ResponseBody
    @RequestMapping(path = "/searchByQueryAndCountry", method = RequestMethod.POST)
    public List<Summary> searchByQueryAndCountry(String query, String country) {
        System.out.println(query);
        System.out.println(country);
//        String[] q = query.split("&&");
//        String[] qu = q[0].split("=");
//        String[] country = q[1].split("=");
//        System.out.println(country);
        return searchService.findByQueryAndCountry(query, country);
    }

    @ResponseBody
    @RequestMapping(path = "/searchByQueryAndSity", method = RequestMethod.POST)
    public List<Summary> searchByQueryAndSity(String query, String sity) {
        System.out.println(query);
        System.out.println(sity);
//        String[] q = query.split("&&");
//        String[] qu = q[0].split("=");
//        String[] sity = q[1].split("=");
        return searchService.findByQueryAndSity(query, sity);
    }

    @ResponseBody
    @RequestMapping(path = "/searchByQueryAndSityAndCountry", method = RequestMethod.POST)
    public List<Summary> searchByQueryAndSityAndCountry(String query, String country, String sity) {
        System.out.println(query);
        System.out.println(country);
        System.out.println(sity);
//        String[] q = query.split("&&");
//        String[] qu = q[0].split("=");
//        String[] country = q[1].split("=");
//        String[] sity = q[2].split("=");
        return  searchService.findByQueryAndSityAndCountry(query, country, sity);
    }

    @ResponseBody
    @RequestMapping(path = "/searchBySityAndCountry", method = RequestMethod.POST)
    public List<Summary> searchBySityAndCountry(String country, String sity) {
        System.out.println(country);
        System.out.println(sity);
//        String[] q = country.split("&&");
//        String[] c = q[0].split("=");
//        String[] sity = q[1].split("=");
        return searchService.findByCountryAndSity(country, sity);

    }

    @ResponseBody
    @RequestMapping(path = "/searchByCountry", method = RequestMethod.POST)
    public List<Summary> searchByCountry(String country) {
        System.out.println(country);
//        String[] country = q.split("=");
        return searchService.findByCountry(country);

    }

    @ResponseBody
    @RequestMapping(path = "/searchBySity", method = RequestMethod.POST)
    public List<Summary> searchBySity(@RequestParam String sity) {
//        String[] sity = q.split("=");
        System.out.println(sity);
        return searchService.findBySity(sity);

    }

}
