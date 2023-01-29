package com.example.demo.global;

import com.example.demo.exception.BlogException;
import com.example.demo.model.enums.ErrorInfoEnum;
import com.example.demo.model.vo.Results;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;


/**
 * @Classname GlobalExceptionHandler
 * @Description 全局异常处理
 * @Date 2023/1/23 22:27
 * @Created by orcakill
 */
@Log4j2 //Log4j2  注解，自动注入log对象到容器，可以使用log对象打印日志
@ControllerAdvice  //表示这个类用于全局异常处理
public class GlobalExceptionHandler {
  @ResponseBody
  @ExceptionHandler(value = NoHandlerFoundException.class)//表示此类处理什么类型的异常
  @ResponseStatus(HttpStatus.NOT_FOUND)//返回的http状态码
  public Results<?> noHandlerFoundExceptionHandler(NoHandlerFoundException exception) {
    log.error("NoHandlerFoundException:{}", exception.getMessage());
    return Results.fromErrorInfo(ErrorInfoEnum.RESOURCES_NOT_FOUND);
  }

  @ResponseBody
  @ExceptionHandler(value = Exception.class)//表示此类处理什么类型的异常
  @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
  public Results<?> exceptionHandler(Exception exception) {
    exception.printStackTrace();
    log.error("Exception:{}", exception.getMessage());
    return Results.fromErrorInfo(ErrorInfoEnum.UNKNOWN_ERROR);
  }

  @ResponseBody
  @ExceptionHandler(value = BlogException.class)//表示此类处理什么类型的异常
  @ResponseStatus(HttpStatus.OK) //返回的http状态码
  public Results<?> blogExceptionHandler(BlogException exception) {
    log.error("BlogException:{}", exception.getMessage());
    return exception.toResultVO();
  }

  @ResponseBody
  @ExceptionHandler(MethodArgumentNotValidException.class)//表示此类处理什么类型的异常
  @ResponseStatus(HttpStatus.OK)  //返回的http状态码
  public Results<?> handleValidationExceptions(MethodArgumentNotValidException e) {
    Map<String, String> errors = new HashMap<>();
    e.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });
    return Results.error("参数错误", errors);
  }
}
