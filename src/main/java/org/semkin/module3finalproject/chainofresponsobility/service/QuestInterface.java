package org.semkin.module3finalproject.chainofresponsobility.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.ModelAndView;


public interface QuestInterface {
    void setNextHandler(QuestInterface handler);

    boolean canHandle(HttpServletRequest request);

    ModelAndView handle(ModelAndView modelAndView,
                        HttpServletRequest request,
                        Integer answer);
}