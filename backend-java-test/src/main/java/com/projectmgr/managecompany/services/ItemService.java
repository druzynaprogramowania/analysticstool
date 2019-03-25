package com.projectmgr.managecompany.services;

import com.projectmgr.managecompany.exceptions.ItemIdException;
import com.projectmgr.managecompany.models.Item;
import com.projectmgr.managecompany.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService{

    @Autowired
    private ItemRepository itemRepository;


    public Item saveOrUpdateItem(Item item){

//        if(item.getId() != null){
//            Item existingItem = itemRepository.findByItemIdentifier(item.getItemIdentifier());
//            if(existingItem !=null &&(!existingItem.getProjectLeader().equals(username))){
//                throw new ItemNotFoundException("Project not found in your account");
//            }else if(existingItem == null){
//                throw new ItemNotFoundException("Project with ID: '"+item.getItemIdentifier()+"' cannot be updated because it doesn't exist");
//            }
//        }


        try {
//            User user = userRepository.findByUsername(username);
//            item.setUser(user);
//            item.setProjectLeader(user.getUsername());

            item.setItemIdentifier(item.getItemIdentifier().toUpperCase());

            return itemRepository.save(item);

        } catch (Exception e){
            throw new ItemIdException("Item ID " + item.getItemIdentifier().toUpperCase()+ " already exist");
        }
    }

    public Item findItemByIdentifier (String itemId){

        Item item = itemRepository.findByItemIdentifier(itemId.toUpperCase());

        if (item == null){
            throw new ItemIdException("Item ID " + itemId + " doesn't exist");
        }

        return item;
    }

    public Iterable<Item> findAllItems(){
        return itemRepository.findAll();
    }

    public void deleteItemByIdentifier(String itemId){
//        Item item = itemRepository.findByItemIdentifier(itemId);

//        if (item == null){
//            throw new ItemIdException("Cannot find Item with ID" + itemId + ". This item doesn't exist");
//        }

        Item item = itemRepository.findByItemIdentifier(itemId.toUpperCase());

        if(item == null) {
            throw new ItemIdException("Cannot Item with ID '" + itemId + "' . This item does not exist");
        }
        itemRepository.delete(item);
//        itemRepository.delete(findItemByIdentifier(itemId));
    }

}
