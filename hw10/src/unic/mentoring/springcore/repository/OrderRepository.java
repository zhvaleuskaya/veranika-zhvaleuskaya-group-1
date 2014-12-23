package unic.mentoring.springcore.repository;

import java.util.List;

import unic.mentoring.springcore.data.Order;

public interface OrderRepository
{
	Order getOrderById(Long id);
	Long createOrder(Order order);
	void updateOrder(Order order);
	List<Order> getOrdersByUserId(Long userId);
}