package unic.mentoring.springcore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import unic.mentoring.springcore.api.ItemService;
import unic.mentoring.springcore.api.OrderService;
import unic.mentoring.springcore.api.ProductService;
import unic.mentoring.springcore.api.ProposalService;
import unic.mentoring.springcore.api.SellerService;
import unic.mentoring.springcore.api.UserService;
import unic.mentoring.springcore.api.impl.ItemServiceImpl;
import unic.mentoring.springcore.api.impl.OrderServiceImpl;
import unic.mentoring.springcore.api.impl.ProductServiceImpl;
import unic.mentoring.springcore.api.impl.ProposalServiceImpl;
import unic.mentoring.springcore.api.impl.SellerServiceImpl;
import unic.mentoring.springcore.api.impl.UserServiceImpl;
import unic.mentoring.springcore.repository.ItemRepository;
import unic.mentoring.springcore.repository.ProductRepository;
import unic.mentoring.springcore.repository.ProposalRepository;

@Configuration
public class ShopServiceConfig {
    
    @Autowired
    private ItemRepository itemRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private ProposalRepository proposalRepository;
    
    @Bean
    public ItemService itemService() {
        return new ItemServiceImpl(itemRepository);
    }
    
    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl();
    }
    
    @Bean
    public ProductService productService() {
        return new ProductServiceImpl(productRepository);
    }
    
    @Bean
    public SellerService sellerService() {
        return new SellerServiceImpl();
    }
    
    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }
    
    @Bean
    public ProposalService proposalService() {
        return new ProposalServiceImpl(proposalRepository, sellerService(), productService());
    }
}
