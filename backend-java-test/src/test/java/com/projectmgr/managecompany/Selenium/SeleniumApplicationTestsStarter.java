package com.projectmgr.managecompany.Selenium;

import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SeleniumApplicationTestsStarter {

    @Test
    @Order(1)
    public void a_Login() throws Exception {
        LoginTestCase loginTestCase = new LoginTestCase();
        loginTestCase.setupTest();
        loginTestCase.firstTest();
        loginTestCase.teardownTest();
    }

    @Test
    @Order(2)
    public void b_AddItem() throws Exception {
        AddItemTestCase addItemTestCase = new AddItemTestCase();
        addItemTestCase.setUp();
        addItemTestCase.testAdd();
        addItemTestCase.tearDown();
    }

    @Test
    @Order(3)
    public void  c_UpdateItem() throws Exception{
        UpdateItemTestCase updateItemTestCase = new UpdateItemTestCase();
        updateItemTestCase.setUp();
        updateItemTestCase.testUpdateItemTestCase();
        updateItemTestCase.tearDown();
    }

    @Test
    @Order(4)
    public void d_DeleteItem() throws Exception {
        DeleteItemTestCase deleteItemTestCase = new DeleteItemTestCase();
        deleteItemTestCase.setUp();
        deleteItemTestCase.testDeleteItemTestCase();
        deleteItemTestCase.tearDown();
    }

}
