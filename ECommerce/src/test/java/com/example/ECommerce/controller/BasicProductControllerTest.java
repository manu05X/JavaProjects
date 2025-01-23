package com.example.ECommerce.controller;

import org.junit.jupiter.api.Test;

public class BasicProductControllerTest {

    //Basic

    @Test
    public void testMathOperations(){
        int res = add(3,2);
        //assert(res == 3); // normal assert

        //Assertions.assertEquals(5, res, ) // JUnit assert it takes 5 Assertions.assertEquals(expected Result, My result, Appropriate Message);
        //Assertions.assertEquals( 5, res, "Addition result is ");

//                Integer result2 = add2(1,2);
//
//        Assertions.assertNull(result2);

//        Assertions.assertThrows(NullPointerException.class,
//                () -> call());

//        boolean flag = false;
//        try {
//            call();
//        } catch (Exception e) {
//            Assertions.assertEquals(0,0);
//            flag = true;
//        }
//
//        if(flag == false) {
//            Assertions.assertEquals(0,1);
//        }

        //Assertions.assertThrows(NullPointerException.class, () -> doSomething());
    }

    private int add(int a, int b){
        return a + b ;
    }

    private Integer add2(int a, int b) {
        return null;
    }

    private void doSomething() {
        throw new NullPointerException();
    }




}
