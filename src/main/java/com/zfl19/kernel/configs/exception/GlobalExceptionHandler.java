package com.zfl19.kernel.configs.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author: 19zfl
 * @description:
 * @date 2023-05-26
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 自定义异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(CustomException.class)
    public void myException(CustomException e) {
        System.out.println(e.getMessage());
    }

}
