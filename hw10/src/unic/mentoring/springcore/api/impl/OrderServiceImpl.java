package unic.mentoring.springcore.api.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import unic.mentoring.springcore.api.ItemService;
import unic.mentoring.springcore.api.OrderService;
import unic.mentoring.springcore.data.Item;
import unic.mentoring.springcore.data.Order;
import unic.mentoring.springcore.data.Proposal;
import unic.mentoring.springcore.data.User;
import unic.mentoring.springcore.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource(name = "orderRepository")
    private OrderRepository orderRepository;
    
    @Resource(name = "itemService")
    private ItemService itemService;

    @Override
    public Long createOrder(User user, Item... items) {
        //create and save order object
        Order order = new Order();
        order.setUser(user);
        order.setCreatedDate(new Date());
        
        Long orderId = orderRepository.createOrder(order);
        
        //save item objects
        for (Item item : items) {
            item.setOrder(order);
            itemService.createItem(item);
        }
        
        return orderId;
    }
    
    public Long createOrder(User user, Proposal... proposals) {
        List<Item> items = new ArrayList<Item>();
        
        for (Proposal proposal : proposals) {
            Item item = new Item();
            item.setProduct(proposal.getProduct());
            item.setPrice(proposal.getPrice());
            
            items.add(item);
        }
        
        return createOrder(user, items.toArray(new Item[items.size()]));
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.getOrderById(id);
    }

    @Override
    public void updateOrder(Order order) {
        orderRepository.updateOrder(order);
    }

    @Override
    public List<Order> getOrdersByUser(User user) {
        return getOrdersByUserId(user.getId());
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.getOrdersByUserId(userId);
    }
}
