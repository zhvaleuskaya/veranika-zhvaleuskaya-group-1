package unic.mentoring.springcore.repository.map;

import java.util.List;

import org.apache.commons.collections4.Predicate;

import unic.mentoring.springcore.data.Item;
import unic.mentoring.springcore.repository.ItemRepository;

public class ItemMapRepository extends AbstractMapRepository<Item> implements ItemRepository
{
	@Override
	public Long createItem(Item item)
	{
		return create(item);
	}

	@Override
	public void deleteItem(Long itemId)
	{
		deleteItem(itemId);
	}

	@Override
	public List<Item> getItemsByOrderId(Long orderId)
	{
		return select(new ItemByOrderPredicate(orderId));
	}

	private class ItemByOrderPredicate implements Predicate
	{
		private Long orderId;

		private ItemByOrderPredicate(Long orderId)
		{
			super();
			this.orderId = orderId;
		}

		@Override
		public boolean evaluate(Object input)
		{
			if (input instanceof Item)
			{
				Item item = (Item) input;
				return orderId.equals(item.getOrder().getId());
			}

			return false;
		}
	}
}