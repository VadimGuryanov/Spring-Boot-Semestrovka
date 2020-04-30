package ru.itis.springboothomework.controllers;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.springboothomework.service.interfaces.UserService;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionController {

    @Autowired
    private UserService service;

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView notFound(HttpServletRequest req, Exception exception) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", exception);
        mav.addObject("url", req.getRequestURL());
        if (service.getCurrentUser() == null) {
            mav.addObject("anon");
        }
        mav.setViewName("error/404");
        return mav;
    }


    @ExceptionHandler(HttpClientErrorException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ModelAndView forbidden(HttpServletRequest req, Exception exception) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", exception);
        mav.addObject("url", req.getRequestURL());
        if (service.getCurrentUser() == null) {
            mav.addObject("anon");
        }
        mav.setViewName("error/403");
        return mav;
    }




}
