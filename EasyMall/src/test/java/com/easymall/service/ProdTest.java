package com.easymall.service;

import com.easymall.factory.BasicFactory;
import com.easymall.pojo.Product;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ProdTest {
    ProdService prodService;

    @Before
    public void init() {
        prodService = BasicFactory.getFactory().getInstance(ProdService.class);
    }

    @Test
    public void findAll() {
        List<Product> list = prodService.findAll();
        for (Product p : list) {
            System.out.println(p);
        }
    }

    @Test
    public void updatePnum() {
        boolean success = prodService.updatePnum("1", 497);
        System.out.println(success);
        success = prodService.updatePnum("100", 497);
        System.out.println(success);
    }

    @Test
    public void delProd() {
        boolean success = prodService.delProd("15");
        System.out.println(success);
        success = prodService.delProd("100");
        System.out.println(success);
    }

    @Test
    public void findAllByCondition() {
        List<Product> list = prodService.findAllByCondition("", "", 0, 999);
        for (Product p : list) {
            System.out.println(p);
        }
    }

    @Test
    public void findAllBySearch() {
        List<Product> list = prodService.findAllBySearch("p");
        for (Product p : list) {
            System.out.println(p);
        }
    }

    @Test
    public void findProdById() {
        Product p = prodService.findProdById("1");
        System.out.println(p == null ? "null" : p);
    }
}