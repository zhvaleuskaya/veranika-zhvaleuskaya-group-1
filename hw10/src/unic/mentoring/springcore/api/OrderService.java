package unic.mentoring.springcore.api;

import java.util.List;

import unic.mentoring.springcore.data.Item;
import unic.mentoring.springcore.data.Order;
import unic.mentoring.springcore.data.Proposal;
import unic.mentoring.springcore.data.User;

/**
 * Provides API for manipulating orders.
 */
public interface OrderService {
    
    /**
     * Gets the order by id.
     *
     * @param id the id
     * @return the order by id
     */
    Order getOrderById(Long id);
    
    /**
     * Creates the order.
     *
     * @param user the user
     * @param items the items
     * @return the order id
     */
    Long createOrder(User user, Item... items);
    
    /**
     * Creates the order.
     *
     * @param user the user
     * @param proposals the proposals
     * @return the order id
     */
    Long createOrder(User user, Proposal... proposals);
    
    /**
     * Update order.
     *
     * @param order the order
     */
    void updateOrder(Order order);
    
    /**
     * Gets the orders by user.
     *
     * @param user the user
     * @return the orders by user
     */
    List<Order> getOrdersByUser(User user);
    
    /**
     * Gets the orders by user id.
     *
     * @param userId the user id
     * @return the orders by user id
     */
    List<Order> getOrdersByUserId(Long userId);
}
