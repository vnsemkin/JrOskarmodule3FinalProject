package org.semkin.module3finalproject;

import jakarta.servlet.http.HttpServletRequest;
import org.semkin.module3finalproject.chainofresponsobility.config.Constants;
import org.semkin.module3finalproject.chainofresponsobility.config.MyAppConfig;
import org.semkin.module3finalproject.chainofresponsobility.service.QuestActionLevel1;
import org.semkin.module3finalproject.chainofresponsobility.service.QuestActionLevel2;
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
    String trueUrl = "/level2";
    String falseUrl = "/level1";
    @Autowired
    QuestActionLevel1 level1;
    @Autowired
    QuestActionLevel2 level2;


    @Test
    public void myHandlerImplementationLevel1CanHandleMethod() {
        //GIVEN
        String trueUrl = Constants.ACTION_LEVEL1;
        String falseUrl = Constants.ACTION_LEVEL2;
        //WHEN
        HttpServletRequest trueExpectedRequest = mock(HttpServletRequest.class);
        when(trueExpectedRequest.getRequestURI()).thenReturn(trueUrl);
        when(trueExpectedRequest.getMethod()).thenReturn(Constants.HTTP_METHOD_POST);

        HttpServletRequest falseExpectedRequest = mock(HttpServletRequest.class);
        when(falseExpectedRequest.getRequestURI()).thenReturn(falseUrl);
        when(falseExpectedRequest.getMethod()).thenReturn(Constants.HTTP_METHOD_GET);
        //THEN
        assertTrue(level1.canHandle(trueExpectedRequest));
        assertFalse(level1.canHandle(falseExpectedRequest));
    }

    //WHEN
    @Test
    public void myHandlerImplementationLevel2CanHandleMethod() {
        //GIVEN
        //WHEN
        HttpServletRequest trueExpectedRequest = mock(HttpServletRequest.class);
        when(trueExpectedRequest.getRequestURI()).thenReturn(trueUrl);
        when(trueExpectedRequest.getMethod()).thenReturn(Constants.HTTP_METHOD_POST);

        HttpServletRequest falseExpectedRequest = mock(HttpServletRequest.class);
        when(falseExpectedRequest.getRequestURI()).thenReturn(falseUrl);
        when(falseExpectedRequest.getMethod()).thenReturn(Constants.HTTP_METHOD_GET);
        //THEN
        assertTrue(level2.canHandle(trueExpectedRequest));
        assertFalse(level2.canHandle(falseExpectedRequest));
    }

    @Test
    public void myHandlerImplementationLevel1HandleMethod() {
        //GIVEN
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn(Constants.ACTION_LEVEL1);
        when(request.getMethod()).thenReturn(Constants.HTTP_METHOD_POST);
        ModelAndView result1 = new ModelAndView();
        ModelAndView result2 = new ModelAndView();
        ModelAndView result3 = new ModelAndView();
        //WHEN
        result1 = level1.handle(result1, request, null);
        result2 = level1.handle(result2, request, 1);
        result3 = level1.handle(result3, request, 2);
        //THEN
        assertEquals(Constants.ACTION_LEVEL1, result1.getViewName());
        assertNull(result1.getModel().get(Constants.ANSWER_ATTRIBUTE));

        assertEquals(Constants.ACTION_LEVEL2, result2.getViewName());
        assertNull(result2.getModel().get(Constants.ANSWER_ATTRIBUTE));

        assertEquals(Constants.GAME_OVER_ENDPOINT, result3.getViewName());
        assertNull(result3.getModel().get(Constants.ANSWER_ATTRIBUTE));
    }

    @Test
    public void myHandlerImplementationLevel2HandleMethod() {
        //GIVEN
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn(Constants.ACTION_LEVEL2);
        when(request.getMethod()).thenReturn(Constants.HTTP_METHOD_POST);
        ModelAndView result1 = new ModelAndView();
        ModelAndView result2 = new ModelAndView();
        ModelAndView result3 = new ModelAndView();
        //WHEN
        result1 = level1.handle(result1, request, null);
        result2 = level1.handle(result2, request, 1);
        result3 = level1.handle(result3, request, 2);
        //THEN
        assertEquals(Constants.ACTION_LEVEL2, result1.getViewName());
        assertNull(result1.getModel().get(Constants.ANSWER_ATTRIBUTE));

        assertEquals(Constants.WIN_ENDPOINT, result2.getViewName());
        assertNull(result2.getModel().get(Constants.ANSWER_ATTRIBUTE));

        assertEquals(Constants.GAME_OVER_ENDPOINT, result3.getViewName());
        assertNull(result3.getModel().get(Constants.ANSWER_ATTRIBUTE));
    }
}


