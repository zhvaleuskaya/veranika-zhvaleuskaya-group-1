package unic.mentoring.springcore.api.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import unic.mentoring.springcore.api.ItemService;
import unic.mentoring.springcore.data.Item;
import unic.mentoring.springcore.repository.ItemRepository;

@Service("itemService")
public class ItemServiceImpl implements ItemService {
    
    private ItemRepository repository;

    @Autowired
    public ItemServiceImpl(ItemRepository repository) {
        super();
        this.repository = repository;
    }

    @Override
    public Long createItem(Item item) {
        return repository.createItem(item);
    }

    @Override
    public void deleteItem(Long itemId) {
        repository.deleteItem(itemId);
    }

    @Override
    public List<Item> getItemsByOrderId(Long orderId) {
        return repository.getItemsByOrderId(orderId);
    }
}
