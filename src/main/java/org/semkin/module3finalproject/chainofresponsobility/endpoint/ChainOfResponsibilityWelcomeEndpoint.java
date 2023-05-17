package org.semkin.module3finalproject.chainofresponsobility.endpoint;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.semkin.module3finalproject.chainofresponsobility.service.MyHandlerImplementationLevel1;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@Controller
public class ChainOfResponsibilityWelcomeEndpoint {
    private HttpSession session;
    private final MyHandlerImplementationLevel1 level1;

    public ChainOfResponsibilityWelcomeEndpoint(MyHandlerImplementationLevel1 level1) {
        this.level1 = level1;
    }

    @GetMapping("/*")
    private String welcomeGetHandler(HttpSession session) {
        this.session = session;
        return "welcome";
    }

    @PostMapping("/*")
    public ModelAndView welcomePostHandler(@RequestParam(required = false) Integer answer,
                                     @RequestParam(required = false) String username,
                                     ModelAndView modelAndView,
                                     HttpServletRequest request) {
        if (Objects.nonNull(username)) session.setAttribute("username", username);
        modelAndView.addObject("username", session.getAttribute("username"));
        return level1.handle(modelAndView, request, answer);
    }
}