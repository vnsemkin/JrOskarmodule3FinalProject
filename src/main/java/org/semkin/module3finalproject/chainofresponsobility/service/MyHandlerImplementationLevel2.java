package org.semkin.module3finalproject.chainofresponsobility.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@Service
public class MyHandlerImplementationLevel2 implements MyHandler {


    @Override
    public void setNextHandler(MyHandler handler) {
    }

    @Override
    public boolean canHandle(HttpServletRequest request) {
        return "/level2".equals(request.getRequestURI())
                && "POST".equals(request.getMethod());
    }

    @Override
    public ModelAndView handle(ModelAndView modelAndView, HttpServletRequest request, Integer answer) {
        if (canHandle(request)) {
            if (Objects.isNull(answer)) {
                modelAndView.setViewName("/level2");
            } else {
                if (answer == 1) {
                    modelAndView.setViewName("/win");
                } else {
                    modelAndView.setViewName("/gameOver");
                }
            }
        }
        return modelAndView;
    }
}