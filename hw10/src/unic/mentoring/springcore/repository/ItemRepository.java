package unic.mentoring.springcore.repository;

import java.util.List;

import unic.mentoring.springcore.data.Item;

public interface ItemRepository
{
	Long createItem(Item item);
	void deleteItem(Long itemId);
	List<Item> getItemsByOrderId(Long orderId);
}