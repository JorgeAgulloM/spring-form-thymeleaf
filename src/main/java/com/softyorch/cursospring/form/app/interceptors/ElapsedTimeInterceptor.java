package com.softyorch.cursospring.form.app.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Random;

@Component("elapsedTimeInterceptor")
public class ElapsedTimeInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(ElapsedTimeInterceptor.class);
    private static final String TIME_START = "timeStart";
    private static final String TIME_ELAPSED = "timeElapsed";

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws Exception {

        log.info("[ElapsedTimeInterceptor] preHandler() entry...");
        long timeStart = System.currentTimeMillis();
        request.setAttribute(TIME_START, timeStart);

        Random random = new Random();
        int delay = random.nextInt(500);
        Thread.sleep(delay);

        return true;
    }

    @Override
    public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            ModelAndView modelAndView
    ) throws Exception {

        long timeEnd = System.currentTimeMillis();
        long timeStart = (long) request.getAttribute(TIME_START);
        long timeElapsed = timeEnd - timeStart;

        if (modelAndView != null) {
            modelAndView.addObject(TIME_ELAPSED, timeElapsed);
        }

        log.info("[ElapsedTimeInterceptor] Elapsed time: " + timeElapsed + " milliseconds");
        log.info("[ElapsedTimeInterceptor] preHandler() exiting...");

    }
}
