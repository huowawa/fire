/*
 * Copyright (c)
 */
package com.soft.fire.common;

import com.soft.fire.exception.APIException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

/**
 * 全局异常处理器
 *
 * @author David Lin
 * @version: 1.0
 * @date 2020-03-27 12:59
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 全局处理 MethodArgumentNotValidException  异常
     *
     * @param methodArguException
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArguException) {
        BindingResult bindingResult = methodArguException.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        StringBuilder messBuilder = new StringBuilder(128);
        for (FieldError fieldError : fieldErrors) {
            messBuilder.append(fieldError.getDefaultMessage()).append(";");
        }
        return new Result<>(ResultCode.VALIDATE_FAILED, messBuilder.toString());

    }

    /**
     * 全局处理ConstraintViolationException异常
     *
     * @param constraintViolationEx
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Result<String> handleConstraintViolationException(ConstraintViolationException constraintViolationEx) {
        StringBuilder messBuilder = new StringBuilder(64);
        Set<ConstraintViolation<?>> constraintViolations = constraintViolationEx.getConstraintViolations();

        //使用Java8的  Lambda 表达式 简化
        constraintViolations.forEach(validate -> {
            messBuilder.append(validate.getMessage());
        });

        return new Result<>(ResultCode.VALIDATE_FAILED, messBuilder.toString());
    }

    /**
     * 全局处理MissingServletRequestParameterException异常
     *
     * @param missEx
     * @return
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result<String> handleMissingServletRequestParameterException(MissingServletRequestParameterException missEx) {
        StringBuilder messBuilder = new StringBuilder(64);
        messBuilder.append(missEx.getParameterName()).append("参数不能为空");

        return new Result<>(ResultCode.FAILED, messBuilder.toString());
    }

    /**
     * 全局处理自定义异常
     *
     * @param apiEx
     * @return
     */
    @ExceptionHandler(APIException.class)
    public Result<String> handleApiException(APIException apiEx) {
        return new Result<>(ResultCode.FAILED, apiEx.getMsg());
    }

}
