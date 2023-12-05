package com.softyorch.cursospring.form.app.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Random;

@Component("elapsedTimeInterceptor")
public class ElapsedTimeInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(ElapsedTimeInterceptor.class);
    private static final String TIME_START = "timeStart";
    private static final String TIME_ELAPSED = "timeElapsed";
    private static final String POST = "post";

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws Exception {

        if (request.getMethod().equalsIgnoreCase(POST)) return true;

        log.info("[ElapsedTimeInterceptor] preHandler() entry...");

        if (handler instanceof HandlerMethod method)
            log.info("[ElapsedTimeInterceptor] Intercepted handle name: " + method.getMethod().getName());

        long timeStart = System.currentTimeMillis();
        request.setAttribute(TIME_START, timeStart);

        Random random = new Random();
        int delay = random.nextInt(500);
        Thread.sleep(delay);

        return true;

        //En caso de que falle alguna validación y evitar que se invoque el método invocado.
        //response.sendRedirect(request.getContextPath().concat("/loggin"));
        //return false;
    }

    @Override
    public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            ModelAndView modelAndView
    ) throws Exception {

        if (request.getMethod().equalsIgnoreCase(POST)) return;

        long timeEnd = System.currentTimeMillis();
        long timeStart = (long) request.getAttribute(TIME_START);
        long timeElapsed = timeEnd - timeStart;

        if (handler instanceof HandlerMethod && modelAndView != null)
            modelAndView.addObject(TIME_ELAPSED, timeElapsed);


        log.info("[ElapsedTimeInterceptor] Elapsed time: " + timeElapsed + " milliseconds");
        log.info("[ElapsedTimeInterceptor] postHandler() exiting...");

    }
}
