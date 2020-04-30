package ru.itis.springboothomework.controllers;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import ru.itis.springboothomework.dto.SummaryDto;
import ru.itis.springboothomework.models.Summary;
import ru.itis.springboothomework.service.interfaces.CategoryService;
import ru.itis.springboothomework.service.interfaces.CountryService;
import ru.itis.springboothomework.service.interfaces.SummaryService;
import ru.itis.springboothomework.service.interfaces.UserService;
import ru.itis.springboothomework.utils.HelperError;

import javax.validation.Valid;
import java.sql.SQLOutput;

@Controller
public class SummaryController {

    @Autowired
    private UserService userService;

    @Autowired
    private SummaryService summaryService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private HelperError helper;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/summary/new", method = RequestMethod.GET)
    public String newSummary(ModelMap map) {
        map.put("categories", helper.getOtherCategories(categoryService.getCategories(), null));
        map.put("user", userService.getCurrentUser());
        mapLocation(map);
        return "summary_new";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/summary/new", method = RequestMethod.POST)
    public String newSummary(
            @Valid SummaryDto summaryDto,
            ModelMap map,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            map.put("errors", helper.errors(result.getFieldErrors()));
            map(map, null, summaryDto);
            return "summary_new";
        } else if (helper.isCategoriesEmpty(summaryDto.getCustomCategory(), summaryDto.getSummaryCategories())) {
            map(map, "Категория не должна быть пустой", summaryDto);
            return "summary_new";
        } else if (helper.isCategoriesConstraintNewCategory(summaryDto.getCustomCategory(), categoryService.getCategories())){
            map(map, "Новая категория не должна совпадать с категориями из списка", summaryDto);
            return "summary_new";
        } else {
            long id = summaryService.save(summaryDto);
            return "redirect:" + MvcUriComponentsBuilder.fromMappingName("SC#pageSummary").arg(0, id).build();
        }
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/summary/edit/{id}", method = RequestMethod.POST)
    public String editSummary(
            @PathVariable("id") Summary summary,
            @Valid SummaryDto summaryDto,
            ModelMap map,
            BindingResult result
    ) {

        if (result.hasErrors()) {
            map.put("errors", helper.errors(result.getFieldErrors()));
            map(map, null, summaryDto);
            return "summary_red";
        } else if (helper.isCategoriesEmpty(summaryDto.getCustomCategory(), summaryDto.getSummaryCategories())) {
            map(map, "Категория не должна быть пустой", summaryDto);
            return "summary_red";
        } else if (helper.isCategoriesConstraintNewCategory(summaryDto.getCustomCategory(), categoryService.getCategories())){
            map(map, "Новая категория не должна совпадать с категориями из списка", summaryDto);
            return "summary_red";
        } else {
            long id = summaryService.update(summaryDto, summary);
            System.out.println(id);
            return "redirect:" + MvcUriComponentsBuilder.fromMappingName("SC#pageSummary").arg(0, id).build();
        }
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/summary/edit/{id}", method = RequestMethod.GET)
    public String editSummary(@PathVariable("id") Summary summary, ModelMap map) throws NotFoundException, HttpClientErrorException  {
        if (helper.checkSummary(userService.getCurrentUser().getSummaries(), summary)) {
            map.put("categories", helper.getOtherCategories(categoryService.getCategories(), summary.getSummaryCategories()));
            map.put("user", summary.getUser());
            map.put("summary", summary);
            String[] cost = summary.getCost().split(" ");
            map.put("cost", cost[0]);
            map.put("cost_val", cost[1]);
            mapLocation(map);
            return "summary_red";
        }
        throw new HttpClientErrorException(HttpStatus.FORBIDDEN);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/summary/delete/{id}", method = {RequestMethod.GET,RequestMethod.POST})
    public String deleteSummary(@PathVariable("id") long id, ModelMap map) throws NotFoundException, HttpClientErrorException {
        Summary summary = summaryService.getById(id);
        if (helper.checkSummary(userService.getCurrentUser().getSummaries(), summary)) {
            summaryService.delete(summary);
            return "redirect:" + MvcUriComponentsBuilder.fromMappingName("PC#profile").build();
        }
        throw new HttpClientErrorException(HttpStatus.FORBIDDEN);
    }




    @RequestMapping(path = "/summary/{id}", method = RequestMethod.GET)
    public String pageSummary(@PathVariable("id") long id, ModelMap map) throws NotFoundException {
        Summary summary = summaryService.getById(id);
        map.put("summary", summary);
        map.put("description", summary.getDescription().split("\n"));
        map.put("anon", userService.getCurrentUser());
        if (userService.getCurrentUser() != null && helper.checkSummary(userService.getCurrentUser().getSummaries(), summary)) {
            return "summary";
        } else {
            return "summary_other";
        }
    }

    private void map(ModelMap map, String message, SummaryDto summaryDto) {
        map.put("user", userService.getCurrentUser());
        map.put("categories", categoryService.getCategories());
        map.put("countries", countryService.getCountries());
        map.put("sities", countryService.getCountry(userService.getCurrentUser().getCountry()).getSities());
        map.put("isSave", "");
        map.put("summary", summaryDto);
        map.put("cost", summaryDto.getCost());
        map.put("message", message);
        mapLocation(map);
    }

    private void mapLocation(ModelMap map) {
        map.put("countries", countryService.getCountries());
        map.put("sities", countryService.getCountry(userService.getCurrentUser().getCountry()).getSities());
    }
}
