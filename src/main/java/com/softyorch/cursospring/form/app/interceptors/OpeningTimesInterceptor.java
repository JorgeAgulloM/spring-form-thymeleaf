package com.softyorch.cursospring.form.app.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Calendar;

@Component("openingTimes")
public class OpeningTimesInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(OpeningTimesInterceptor.class);
    private static final String MESSAGE = "message";
    private static final String GET = "get";
    @Value("${config.time.opening}")
    private int opening;
    @Value("${config.time.closed}")
    private int closed;

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws Exception {

        if (!request.getMethod().equalsIgnoreCase(GET)) return true;

        log.info("[OpeningTimesInterceptor] preHandler() entry...");

        if (handler instanceof HandlerMethod method)
            log.info("[OpeningTimesInterceptor] Intercepted handle name: " + method.getMethod().getName());


        Calendar calendar = Calendar.getInstance();
        int now = calendar.get(Calendar.HOUR_OF_DAY);

        if (now >= opening && now < closed) {
            StringBuilder message = new StringBuilder("Nuestro horario de atención es de las ");
            message.append(opening);
            message.append("hrs. ");
            message.append("hasta las ");
            message.append(closed);
            message.append("hrs. ");
            message.append("Gracias por su visita.");
            request.setAttribute(MESSAGE, message.toString());

            return true;
        }

        response.sendRedirect(request.getContextPath().concat("/closed"));
        return false;
    }

    @Override
    public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            ModelAndView modelAndView
    ) throws Exception {

        if (!request.getMethod().equalsIgnoreCase(GET)) return;

        String message = (String) request.getAttribute(MESSAGE);
        modelAndView.addObject(MESSAGE, message);

    }
}
