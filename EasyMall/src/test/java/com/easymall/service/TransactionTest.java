package com.easymall.service;

import com.easymall.factory.BasicFactory;
import org.junit.Before;
import org.junit.Test;

public class TransactionTest {
    TranTestService testService;

    @Before
    public void init() {
        testService = BasicFactory.getFactory().getInstance(TranTestService.class);
    }

    @Test
    public void test() {
        try {
            testService.modifyUser();
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println("Message: " + e.getCause().getMessage());
        }
    }
}