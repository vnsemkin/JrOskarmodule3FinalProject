package org.semkin.module3finalproject.chainofresponsobility.endpoint;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.semkin.module3finalproject.chainofresponsobility.config.Constants;
import org.semkin.module3finalproject.chainofresponsobility.service.QuestActionLevel1;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@Controller
public class ChainOfResponsibilityWelcomeEndpoint {
    private final QuestActionLevel1 questActionLevel1;

    public ChainOfResponsibilityWelcomeEndpoint(QuestActionLevel1 questActionLevel1) {
        this.questActionLevel1 = questActionLevel1;
    }

    @GetMapping("/*")
    private String welcomeGetHandler() {
        return Constants.WELCOME_PAGE;
    }

    @PostMapping("/*")
    public ModelAndView welcomePostHandler(@RequestParam(required = false) Integer answer,
                                           @RequestParam(required = false) String username,
                                           ModelAndView modelAndView,
                                           HttpServletRequest request, HttpSession session) {
        if (Objects.nonNull(username)){
            session.setAttribute(Constants.USERNAME_ATTRIBUTE, username);
        }
        modelAndView.addObject(Constants.USERNAME_ATTRIBUTE, session.getAttribute(Constants.USERNAME_ATTRIBUTE));
        return questActionLevel1.handle(modelAndView, request, answer);
    }
}