package com.example.ECommerce.controller.advices;

import com.example.ECommerce.dto.ExceptionDto;
import com.example.ECommerce.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ProductControllerAdvice {
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    private ExceptionDto handleProductNotFoundException(ProductNotFoundException e){
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(e.getMessage());
        exceptionDto.setStatus("Failure");

        return exceptionDto;
    }
}