package com.projectmgr.managecompany.controllers;


import com.projectmgr.managecompany.models.Item;
import com.projectmgr.managecompany.services.ItemService;
import com.projectmgr.managecompany.services.MapValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    //creating the route
    //BindingResult analyzes the object and it determines whether or not there are errors
    @PostMapping("")
    public ResponseEntity<?> createNewItem(@Valid @RequestBody Item item, BindingResult result, Principal principal){ // Principal we use when someone don't have access to token

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap!=null) {
            return errorMap;
        }

        //happy path
        Item item1 = itemService.saveOrUpdateItem(item, principal.getName());
        return new ResponseEntity<Item>(item1,HttpStatus.CREATED);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<?> getItemById(@PathVariable String itemId, Principal principal){

        Item item = itemService.findItemByIdentifier(itemId, principal.getName());

        return new ResponseEntity<Item>(item, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Item> getAllItems(){
        return itemService.findAllItems();
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<?> deleteItem (@PathVariable String itemId, Principal principal){
        itemService.deleteItemByIdentifier(itemId, principal.getName());

        return new ResponseEntity<String>("Item with ID " + itemId + " was deleted", HttpStatus.OK);
    }
}
