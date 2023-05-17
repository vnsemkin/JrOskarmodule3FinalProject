package org.semkin.module3finalproject;

import jakarta.servlet.http.HttpServletRequest;
import org.semkin.module3finalproject.chainofresponsobility.config.MyAppConfig;
import org.semkin.module3finalproject.chainofresponsobility.service.MyHandlerImplementationLevel1;
import org.semkin.module3finalproject.chainofresponsobility.service.MyHandlerImplementationLevel2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@ContextConfiguration(classes = MyAppConfig.class)
class Module3FinalProjectApplicationTests {
    static final String GET = "GET";
    static final String POST = "POST";
    @Autowired
    MyHandlerImplementationLevel1 level1;
    @Autowired
    MyHandlerImplementationLevel2 level2;


    @Test
    public void myHandlerImplementationLevel1CanHandleMethod() {
        //GIVEN
        String trueUrl = "/level1";
        String falseUrl = "/level2";
        //WHEN
        HttpServletRequest trueExpectedRequest = mock(HttpServletRequest.class);
        when(trueExpectedRequest.getRequestURI()).thenReturn(trueUrl);
        when(trueExpectedRequest.getMethod()).thenReturn(POST);

        HttpServletRequest falseExpectedRequest = mock(HttpServletRequest.class);
        when(falseExpectedRequest.getRequestURI()).thenReturn(falseUrl);
        when(falseExpectedRequest.getMethod()).thenReturn(GET);
        //THEN
        assertTrue(level1.canHandle(trueExpectedRequest));
        assertFalse(level1.canHandle(falseExpectedRequest));
    }

    @Test
    public void myHandlerImplementationLevel2CanHandleMethod() {
        //GIVEN
        String trueUrl = "/level2";
        String falseUrl = "/level1";
        //WHEN
        HttpServletRequest trueExpectedRequest = mock(HttpServletRequest.class);
        when(trueExpectedRequest.getRequestURI()).thenReturn(trueUrl);
        when(trueExpectedRequest.getMethod()).thenReturn(POST);

        HttpServletRequest falseExpectedRequest = mock(HttpServletRequest.class);
        when(falseExpectedRequest.getRequestURI()).thenReturn(falseUrl);
        when(falseExpectedRequest.getMethod()).thenReturn(GET);
        //THEN
        assertTrue(level2.canHandle(trueExpectedRequest));
        assertFalse(level2.canHandle(falseExpectedRequest));
    }

    @Test
    public void myHandlerImplementationLevel1HandleMethod() {
        //GIVEN
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("/level1");
        when(request.getMethod()).thenReturn(POST);
        ModelAndView result1 = new ModelAndView();
        ModelAndView result2 = new ModelAndView();
        ModelAndView result3 = new ModelAndView();
        //WHEN
        result1 = level1.handle(result1, request, null);
        result2 = level1.handle(result2, request, 1);
        result3 = level1.handle(result3, request, 2);
        //THEN
        assertEquals("/level1", result1.getViewName());
        assertNull(result1.getModel().get("answer"));

        assertEquals("/level2", result2.getViewName());
        assertNull(result2.getModel().get("answer"));

        assertEquals("/gameOver", result3.getViewName());
        assertNull(result3.getModel().get("answer"));
    }

    @Test
    public void myHandlerImplementationLevel2HandleMethod() {
        //GIVEN
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("/level2");
        when(request.getMethod()).thenReturn(POST);
        ModelAndView result1 = new ModelAndView();
        ModelAndView result2 = new ModelAndView();
        ModelAndView result3 = new ModelAndView();
        //WHEN
        result1 = level1.handle(result1, request, null);
        result2 = level1.handle(result2, request, 1);
        result3 = level1.handle(result3, request, 2);
        //THEN
        assertEquals("/level2", result1.getViewName());
        assertNull(result1.getModel().get("answer"));

        assertEquals("/win", result2.getViewName());
        assertNull(result2.getModel().get("answer"));

        assertEquals("/gameOver", result3.getViewName());
        assertNull(result3.getModel().get("answer"));
    }
}

