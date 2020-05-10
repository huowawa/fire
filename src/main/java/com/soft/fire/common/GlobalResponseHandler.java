/*
 * Copyright (c)
 */
package com.soft.fire.common;

import com.alibaba.fastjson.JSON;
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

        //log.info("返回的类型为：{}", methodParameter.getGenericParameterType().getTypeName());
        // 如果接口返回的类型本身就是Result那就没有必要进行额外的操作，返回false
        return !methodParameter.getGenericParameterType().getTypeName()
                .equals("com.soft.fire.common.Result<java.lang.String>");
    }

    @Override
    public Object beforeBodyWrite(Object object, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if(methodParameter.getGenericParameterType().getTypeName().equals("java.lang.String")){
            //String类型不能直接包装，所以要进行些特别的处理 会报java.lang.ClassCastException
            Result result = new Result(object);
            // 将数据包装在Result里后，再转换为json字符串响应给客户端
            return JSON.toJSONString(result);
        }
        return new Result<>(object);
    }


}
