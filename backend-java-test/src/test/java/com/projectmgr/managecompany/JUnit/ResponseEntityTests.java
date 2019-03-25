package com.projectmgr.managecompany.JUnit;

import com.projectmgr.managecompany.services.ItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ResponseEntityTests {

    @Test
    public void normal(){

        String itemName = "Item23";
        String description = "IT01";
        String itemIdentifier = "33322";
        String typeOfItem = "Phone";
        String quantity  = "12";
        Integer entity =1;

        ResponseEntity<?> responseEntity = ResponseEntity.status(HttpStatus.OK).header(itemName, description, itemIdentifier, typeOfItem, quantity).body(entity);

        assertNotNull(responseEntity);

    }

    @Test
    public void noBody(){
        ResponseEntity<?> responseEntity = ResponseEntity.ok().build();

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }
}
