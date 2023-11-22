package com.example.springbootthymeleafcrud.controller;

import com.example.springbootthymeleafcrud.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice(annotations = Controller.class)
@RequiredArgsConstructor
public class GlobalExceptionController {

    private final StudentRepository studentRepository;

    @ExceptionHandler(Exception.class)
    public ModelAndView errorPage(Exception exception, Model model){
        String errorMsg = null;
        ModelAndView errorPage = new ModelAndView();
        errorPage.setViewName("index");
        if(exception.getMessage()!=null){
            errorMsg = exception.getMessage();
        }else if (exception.getCause()!=null){
            errorMsg = exception.getCause().toString();
        }else if (exception!=null){
            errorMsg = exception.toString();
        }
        errorPage.addObject("errormsg", errorMsg);
        errorPage.addObject("error",true);
        model.addAttribute("students", studentRepository.findAll());

        return errorPage;
    }
}
