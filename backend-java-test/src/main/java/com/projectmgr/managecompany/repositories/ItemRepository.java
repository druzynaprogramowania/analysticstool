package com.projectmgr.managecompany.repositories;

import com.projectmgr.managecompany.models.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {

    Item findByItemIdentifier(String itemId);

    @Override
    Iterable<Item> findAll();
}
