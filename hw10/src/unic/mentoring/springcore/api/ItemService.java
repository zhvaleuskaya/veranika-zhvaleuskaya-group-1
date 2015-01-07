package unic.mentoring.springcore.api;

import java.util.List;

import unic.mentoring.springcore.data.Item;

/**
 * Provides API for manipulating items.
 */
public interface ItemService {

    /**
     * Creates the item.
     *
     * @param item the item
     * @return the item id
     */
    Long createItem(Item item);

    /**
     * Delete the item.
     *
     * @param itemId the item id
     */
    void deleteItem(Long itemId);

    /**
     * Gets the items by order id.
     *
     * @param orderId the order id
     * @return the items by order id
     */
    List<Item> getItemsByOrderId(Long orderId);
}