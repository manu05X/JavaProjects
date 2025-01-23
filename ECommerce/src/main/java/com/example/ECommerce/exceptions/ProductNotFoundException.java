package com.example.ECommerce.exceptions;

public class ProductNotFoundException extends Exception{
    public ProductNotFoundException(String message){
        super(message);
    }
}