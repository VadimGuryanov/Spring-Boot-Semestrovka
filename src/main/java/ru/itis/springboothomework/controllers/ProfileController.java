package ru.itis.springboothomework.controllers;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.itis.springboothomework.models.User;
import ru.itis.springboothomework.dto.UserFullDto;
import ru.itis.springboothomework.service.interfaces.CountryService;
import ru.itis.springboothomework.service.interfaces.UserService;
import ru.itis.springboothomework.utils.HelperError;

import javax.validation.Valid;

@Controller
public class ProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private HelperError helper;

    @RequestMapping(path = "/profile/{id}", method = RequestMethod.GET)
    public String profileOther(@PathVariable("id") User user, ModelMap map) throws NotFoundException {
        if (user == null) throw new NotFoundException("Not found");
        if (userService.getCurrentUser() == null) {
            map.put("anon", null);
        }
        map.put("user", user);
        map.put("summaries", user.getSummaries());
        map.put("description", user.getDescription());
        return "profile_other";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/profile", method = RequestMethod.GET)
    public String profile(ModelMap map) {
        map.put("user", userService.getCurrentUser());
        map.put("summaries", userService.getCurrentUser().getSummaries());
        String description = userService.getCurrentUser().getDescription();
        map.put("description", description);
//        if (description != null) {
//            if (description.length() > 100) {
//                map.put("description", description.substring(0, 100).split("\n"));
//            } else {
//                map.put("description", description.split("\n"));
//            }
//        }
        return "profile";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/profile/edit", method = RequestMethod.GET)
    public String profileEdit(ModelMap map) {
        map.put("ok", "ok");
        map.put("countries", countryService.getCountries());
        map.put("user", userService.getCurrentUser());
        return "profile_parts/profile_edit";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/profile/edit", method = RequestMethod.POST)
    public String profileEdit(ModelMap map, @Valid UserFullDto user, BindingResult result) throws NotFoundException {
        if (result.hasErrors()) {
            map.put("user", userService.getCurrentUser());
            map.put("errors", helper.errors(result.getFieldErrors()));
        }
        else if (userService.updateUser(user)) {
            map.put("user", userService.getCurrentUser());
            map.put("message", "Успешно.");
        } else {
            map.put("ok", "ok");
            map.put("user", userService.getCurrentUser());
            map.put("message", "Произошла ошибка при редактировании.");
        }
        return "profile_parts/profile_edit";
    }

}
