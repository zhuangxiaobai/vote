package com.item.vote.exception;

import com.item.vote.api.CommonResult;

import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 */
@RestControllerAdvice
public class ExceptionControllerAdvice {

    //处理参数验证的异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResult MethodArgumentNotValidException(MethodArgumentNotValidException e) {
       // CommonResult commonResult;
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);

        return    CommonResult.validateFailed(objectError.getDefaultMessage());
    }

    //处理自定义业务异常
    @ExceptionHandler(BusinessException.class)
    public CommonResult ExceptionHandler(BusinessException e) {
        if (e.getErrorCode() != null) {
            return CommonResult.failed(e.getErrorCode());
        }else {

            if (e.getMessage() !=null){
                return CommonResult.failed(e.getMessage());
            }
            return CommonResult.failed();
        }


    }
}