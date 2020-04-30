package ru.itis.springboothomework.utils;

import javassist.NotFoundException;
import org.springframework.ui.ModelMap;
import org.springframework.validation.FieldError;
import ru.itis.springboothomework.dto.UserDto;
import ru.itis.springboothomework.models.Category;
import ru.itis.springboothomework.models.Summary;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class HelperError {

    public void repeat(ModelMap map, UserDto user) {
        map.put("name", user.getName());
        map.put("lastName", user.getLastName());
        map.put("gender", user.getGender());
        map.put("login", user.getLogin());
        map.put("sity", user.getSity());
        map.put("country", user.getCountry());
        map.put("age", user.getAge());
        map.put("password", user.getPassword());
        map.put("password_repeat", user.getPasswordRepeat());
    }

    public boolean checkPassword(String password, String passwordRepeat) {
        return password.equals(passwordRepeat);
    }


    public Set<String> errors(List<FieldError> filedErrors) {
        Set<String> errors = new HashSet<>();
        System.out.println(filedErrors);
        for (FieldError err: filedErrors) {
            switch (err.getField()) {
                case "passwordRepeat":
                case "password": {
                    errors.add("Пароли: " + err.getDefaultMessage());
                    break;
                }
                case "login": {
                    errors.add("Логин: " + err.getDefaultMessage());
                    break;
                }
                case "name": {
                    errors.add("Имя: " + err.getDefaultMessage());
                    break;
                }
                case "lastName": {
                    errors.add("Фамилия: " + err.getDefaultMessage());
                    break;
                }
                case "age": {
                    errors.add("Возраст: должен быть не пустой, не меньше 10");
                    break;
                }
                case "country":
                case "sity": {
                    errors.add("Страна и город: не должны быть пустыми");
                    break;
                }
                case "title": {
                    errors.add("Название не должно быть пустое");
                    break;
                }
                case "work": {
                    errors.add("Место работы не должно быть пустое");
                    break;
                }
                case "experience": {
                    errors.add("Опыт работы не должен быть пустой. Если его нет то оставте прочерк.");
                    break;
                }
                case "description": {
                    errors.add("Обязательно расскажите об услуги.");
                    break;
                }
                default: errors.add(err.getField());
            }
        }
        return errors;
    }

    public boolean check(UserDto user, ModelMap map) {
        String message = "";
        boolean check = true;
        if (!user.getPasswordRepeat().equals(user.getPassword())) {
            message = "Пароли не совпадают\n";
            check = false;
        }
        if (user.getCountry().equals("-") || user.getSity().equals("-")) {
            message += "Страна и город: не должны быть пустыми";
            check = false;
        }
        map.put("message", message);
        return check;
    }

    public boolean isCategoriesEmpty(String customCategory, List<String> summaryCategories) {
        if (customCategory == null) {
            return summaryCategories == null || summaryCategories.isEmpty();
        }
        return false;
    }

    public boolean isCategoriesConstraintNewCategory(String customCategory, List<Category> summaryCategories) {
        if (summaryCategories == null) return false;
        for (Category c: summaryCategories) {
            if (c.getName().equals(customCategory)) {
                return true;
            }
        }
        return false;
    }

    public Set<Category> getOtherCategories(List<Category> categories, Set<Category> summaryCategories) {
        if (categories == null) return null;
        Set<String> set = new HashSet<>();
        if (summaryCategories == null || summaryCategories.isEmpty()) {
            set.addAll(categories.stream().map(Category::getName).collect(Collectors.toSet()));
        } else {
            set.addAll(categories.stream().map(Category::getName).collect(Collectors.toSet()));
            set.removeAll(summaryCategories.stream().map(Category::getName).collect(Collectors.toSet()));
        }
        Set<Category> categ = new HashSet<>();
        for (String s: set) {
            Category c = new Category();
            c.setName(s);
            categ.add(c);
        }
        return categ;
    }

    public boolean checkSummary(List<Summary> summaries, Summary summary) throws NotFoundException {
        if (summary == null) throw new NotFoundException("");
        for (Summary s: summaries) {
            if (summary.getId() == s.getId()) {
                return true;
            }
        }
        return false;
    }
}
