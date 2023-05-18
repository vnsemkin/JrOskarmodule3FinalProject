package org.semkin.module3finalproject.chainofresponsobility.service;

import jakarta.servlet.http.HttpServletRequest;
import org.semkin.module3finalproject.chainofresponsobility.config.Constants;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@Service
public class QuestActionLevel2 implements QuestInterface {

    @Override
    public void setNextHandler(QuestInterface handler) {
    }

    @Override
    public boolean canHandle(HttpServletRequest request) {
        return Constants.ACTION_LEVEL2.equals(request.getRequestURI())
                && Constants.HTTP_METHOD_POST.equals(request.getMethod());
    }

    @Override
    public ModelAndView handle(ModelAndView modelAndView, HttpServletRequest request, Integer answer) {
        if (canHandle(request)) {
            if (Objects.isNull(answer)) {
               modelAndView.setViewName(Constants.ACTION_LEVEL2);
            } else {
                if (answer == 1) {
                   modelAndView.setViewName(Constants.WIN_ENDPOINT);
                } else {
                   modelAndView.setViewName(Constants.GAME_OVER_ENDPOINT);
                }
            }
        }
        return modelAndView;
    }
}