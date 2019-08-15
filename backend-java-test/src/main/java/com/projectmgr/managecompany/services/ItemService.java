package com.projectmgr.managecompany.services;

import com.projectmgr.managecompany.exceptions.ItemIdException;
import com.projectmgr.managecompany.exceptions.ItemNotFoundException;
import com.projectmgr.managecompany.models.Item;
import com.projectmgr.managecompany.models.User;
import com.projectmgr.managecompany.repositories.ItemRepository;
import com.projectmgr.managecompany.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService{

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;


    public Item saveOrUpdateItem(Item item, String username){

        try {
            User user = userRepository.findByUsername(username);
            item.setUser(user);

            item.setItemIdentifier(item.getItemIdentifier().toUpperCase());

            return itemRepository.save(item);

        } catch (Exception e){
            throw new ItemIdException("Item ID " + item.getItemIdentifier().toUpperCase()+ " already exist");
        }
    }

    public Item findItemByIdentifier (String itemId, String username){

        Item item = itemRepository.findByItemIdentifier(itemId.toUpperCase());

        if (item == null){
            throw new ItemIdException("Item ID " + itemId + " doesn't exist");
        }

        return item;
    }

    public Iterable<Item> findAllItems(){
        return itemRepository.findAll();
    }

    public void deleteItemByIdentifier(String itemId, String username){
        itemRepository.delete(findItemByIdentifier(itemId,username));
    }
}
