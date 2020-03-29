/*
 * Copyright (c)
 */
package com.soft.fire.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 全局响应体处理器
 *
 * @author David Lin
 * @version: 1.0
 * @date 2020-03-28 18:00
 */
@RestControllerAdvice
@Slf4j
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter,
                            Class<? extends HttpMessageConverter<?>> aClass) {

        log.info("返回的类型为：{}", methodParameter.getGenericParameterType().getTypeName());
        // 如果接口返回的类型本身就是Result那就没有必要进行额外的操作，返回false
        return !methodParameter.getGenericParameterType().getTypeName()
                .equals("com.soft.fire.common.Result<java.lang.String>");
    }

    @Override
    public Object beforeBodyWrite(Object object, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        return new Result<>(object);
    }
}
