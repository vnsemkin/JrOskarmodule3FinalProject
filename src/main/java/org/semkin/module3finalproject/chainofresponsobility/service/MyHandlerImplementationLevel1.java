package org.semkin.module3finalproject.chainofresponsobility.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@Service
public class MyHandlerImplementationLevel1 implements MyHandler {
    private MyHandler nextHandler;

    @Override
    public void setNextHandler(MyHandler handler) {
        this.nextHandler = handler;
    }

    @Override
    public boolean canHandle(HttpServletRequest request) {
        return "/level1".equals(request.getRequestURI())
                && "POST".equals(request.getMethod());
    }

    @Override
    public ModelAndView handle(ModelAndView modelAndView,
                               HttpServletRequest request,
                               Integer answer) {
        if (canHandle(request)) {
            if (Objects.isNull(answer)) {
                modelAndView.setViewName("/level1");
            } else {
                if (answer == 1) {
                    modelAndView.setViewName("/level2");
                    modelAndView = nextHandler.handle(modelAndView, request, null);
                } else {
                    modelAndView.setViewName("/gameOver");
                }
            }
            return modelAndView;
        } else {
            nextHandler.handle(modelAndView, request, answer);
        }
        return modelAndView;
    }
}