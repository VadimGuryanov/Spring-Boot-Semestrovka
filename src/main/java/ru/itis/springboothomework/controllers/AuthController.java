package ru.itis.springboothomework.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import ru.itis.springboothomework.dto.UserDto;
import ru.itis.springboothomework.models.Sity;
import ru.itis.springboothomework.service.interfaces.CountryService;
import ru.itis.springboothomework.service.interfaces.UserService;
import ru.itis.springboothomework.utils.HelperError;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AuthController {

    private Logger logger = LogManager.getLogger("auth");

//    @InitBinder
//    private void initBinder(WebDataBinder binder) {
//        binder.setValidator(new UserValidator());
//    }

    @Autowired
    private CountryService countryService;

    @Autowired
    private UserService userService;

    @Autowired
    private HelperError helper;

    @RequestMapping(path = "/signup", method = RequestMethod.GET)
    public String getSignUpPage(ModelMap map) {
        map.put("ok", "ok");
        map.put("countries", countryService.getCountries());
        return "signup";
    }

    @RequestMapping(path = "/signup", method = RequestMethod.POST)
    public String registration(
            @Valid UserDto user,
            BindingResult result,
            ModelMap map
    ) {
        System.out.println(user.getGender());
        if (result.hasErrors()) {
            helper.repeat(map, user);
            map.put("errors", helper.errors(result.getFieldErrors()));
            helper.check(user, map);
            map.put("ok", "no");
            return getSignUpPage(map);
        } else {
            if (!helper.check(user, map)){
                helper.repeat(map, user);
                map.put("ok", "no");
                return getSignUpPage(map);
            }
            if (!userService.addUser(user)) {
                helper.repeat(map, user);
                map.put("ok", "no");
                map.put("message", "Такой пользователь уже существует");
                return getSignUpPage(map);
            } else {
                return "redirect:" + MvcUriComponentsBuilder.fromMappingName("AC#signIn").build();
            }
        }
    }

    @RequestMapping(path = "/signin", method = RequestMethod.GET)
    public String signIn(ModelMap map, HttpServletRequest httpServletRequest) {
//        map.put("_csrf", ((CsrfToken) httpServletRequest.getAttribute("_csrf")).getToken());
        return "signin";
    }

    @ResponseBody
    @RequestMapping(path = "/cities", method = RequestMethod.POST)
    public List<String> getCities(String name) {
        logger.error(name);
        if (!name.equals("-")) {
            return countryService.getCountry(name).getSities().stream()
                    .map(Sity::getName)
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    @RequestMapping(path = "/cities", method = RequestMethod.GET)
    public String ajax(ModelMap map) {
        return "redirect:" + MvcUriComponentsBuilder.fromMappingName("AC#registration").build();
    }

}
