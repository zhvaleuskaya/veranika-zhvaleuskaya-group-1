package unic.mentoring.springcore.repository.map;

import java.util.List;

import org.apache.commons.collections4.Predicate;
import org.springframework.beans.factory.annotation.Value;

import unic.mentoring.springcore.data.Order;
import unic.mentoring.springcore.repository.OrderRepository;

public class OrderMapRepository extends AbstractMapRepository<Order> implements OrderRepository
{
	public OrderMapRepository(long initialSequence)
	{
		this.sequence = initialSequence;
	}
	
	@Value("${repository.order.pk}")
	public void setSequence(long sequence)
	{
		super.sequence = sequence;
	}

	@Override
	public Order getOrderById(Long id)
	{
		return get(id);
	}

	@Override
	public Long createOrder(Order order)
	{
		return create(order);
	}

	@Override
	public void updateOrder(Order order)
	{
		update(order);
	}

	@Override
	public List<Order> getOrdersByUserId(Long userId)
	{
		return select(new OrderByUserPredicate(userId));
	}

	private class OrderByUserPredicate implements Predicate
	{
		private Long userId;

		private OrderByUserPredicate(Long userId)
		{
			super();
			this.userId = userId;
		}

		@Override
		public boolean evaluate(Object input)
		{
			if (input instanceof Order)
			{
				Order order = (Order) input;
				return userId.equals(order.getUser().getId());
			}

			return false;
		}
	}
}