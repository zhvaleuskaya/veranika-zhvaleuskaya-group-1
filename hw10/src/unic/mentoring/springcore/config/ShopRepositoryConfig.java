package unic.mentoring.springcore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import unic.mentoring.springcore.repository.ItemRepository;
import unic.mentoring.springcore.repository.OrderRepository;
import unic.mentoring.springcore.repository.ProductRepository;
import unic.mentoring.springcore.repository.ProposalRepository;
import unic.mentoring.springcore.repository.SellerRepository;
import unic.mentoring.springcore.repository.UserRepository;
import unic.mentoring.springcore.repository.factory.UserRepositoryFactory;
import unic.mentoring.springcore.repository.map.ItemMapRepository;
import unic.mentoring.springcore.repository.map.OrderMapRepository;
import unic.mentoring.springcore.repository.map.ProductMapRepository;
import unic.mentoring.springcore.repository.map.ProposalMapRepository;
import unic.mentoring.springcore.repository.map.SellerMapRepository;

@Configuration
@ImportResource("classpath:resource-config.xml")
public class ShopRepositoryConfig {
    
    @Value("${repository.order.pk}")
    private long initialSequence;
    
    @Autowired
    private UserRepositoryFactory userRepositoryFactory;
    
    @Bean
    public ItemRepository itemRepository() {
        return new ItemMapRepository();
    }
    
    @Bean
    public OrderRepository orderRepository() {
        return new OrderMapRepository(initialSequence);
    }
    
    @Bean
    public ProductRepository productRepository() {
        return new ProductMapRepository();
    }
    
    @Bean
    public ProposalRepository proposalRepository() {
        return new ProposalMapRepository();
    }
    
    @Bean
    public SellerRepository sellerRepository() {
        return new SellerMapRepository();
    }
    
    @Bean
    public UserRepository userRepository() {
        return userRepositoryFactory.createUserRepository();
    }
}
