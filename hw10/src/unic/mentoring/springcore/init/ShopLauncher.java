package unic.mentoring.springcore.init;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import unic.mentoring.springcore.api.ItemService;
import unic.mentoring.springcore.api.OrderService;
import unic.mentoring.springcore.api.ProductService;
import unic.mentoring.springcore.api.ProposalService;
import unic.mentoring.springcore.api.UserService;
import unic.mentoring.springcore.config.ShopConfig;
import unic.mentoring.springcore.data.Item;
import unic.mentoring.springcore.data.Order;
import unic.mentoring.springcore.data.Product;
import unic.mentoring.springcore.data.Proposal;

public class ShopLauncher
{
	private static final Logger LOG = Logger.getLogger(ShopLauncher.class);
	
	public static void main(String[] args)
	{
		ApplicationContext context = new AnnotationConfigApplicationContext(ShopConfig.class);
		
		ProductService productService = context.getBean(ProductService.class);
		OrderService orderService = context.getBean(OrderService.class);
		ItemService itemService = context.getBean(ItemService.class);
		UserService userService = context.getBean(UserService.class);
		ProposalService proposalService = context.getBean(ProposalService.class);

		Product galaxy = productService.getProductsByName("Samsung Galaxy Tab").get(0);
		Proposal proposal = proposalService.getProposalsByProduct(galaxy).get(0);

		orderService.createOrder(userService.getUserById((long) 1), proposal);

		for (Order order : orderService.getOrdersByUserId((long) 1))
		{
			LOG.info(order);

			for (Item item : itemService.getItemsByOrderId(order.getId()))
			{
				LOG.info(item);
			}
		}
	}
}