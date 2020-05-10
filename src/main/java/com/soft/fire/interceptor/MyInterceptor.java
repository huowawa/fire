/*
 * Copyright (c)
 */
package com.soft.fire.interceptor;

import com.soft.fire.annotation.ApiIdempotent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 自定义拦截器
 * 拦截器主要作用是拦截用户的请求并进行相应的处理。比如通过拦截器来进行用户权限验证，或者用来判断当前用户是否已经登录等。
 *
 * @author David Lin
 * @version: 1.0
 * @date 2020-02-09 22:03
 */
@Slf4j
public class MyInterceptor implements HandlerInterceptor {


    /**
     * 该方法将在请求处理之前被调用
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Method method = handlerMethod.getMethod();
        ApiIdempotent annotation = method.getAnnotation(ApiIdempotent.class);
        if(null != annotation){
            log.info("效验接口!!!");
        }
        //log.info("自定义的拦截器调用了1");
        return true;
    }

    /**
     * 该方法在当前请求被处理之后，也就是Controller方法被调用之后执行。
     * 在DispatcherServlet进行视图返回渲染之前被调用
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
    }

    /**
     * 该方法将在整个请求结束之后，也就是DispatcherServlet渲染了对应的视图之后执行。
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {

    }
}
