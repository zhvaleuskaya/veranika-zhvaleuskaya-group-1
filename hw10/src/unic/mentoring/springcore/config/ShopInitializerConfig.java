package unic.mentoring.springcore.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import unic.mentoring.springcore.api.ProductService;
import unic.mentoring.springcore.api.SellerService;
import unic.mentoring.springcore.api.UserService;
import unic.mentoring.springcore.common.Sellers;
import unic.mentoring.springcore.init.DataInitializer;
import unic.mentoring.springcore.init.ProductInitializer;
import unic.mentoring.springcore.init.ProposalInitializer;
import unic.mentoring.springcore.init.SellerInitializer;
import unic.mentoring.springcore.init.UserInitializer;

@Configuration
public class ShopInitializerConfig {

    @Autowired
    private ProductService productService;
    
    @Autowired
    private SellerService sellerService;
    
    @Autowired
    private UserService userService;
    
    @Bean(initMethod = "initData")
    public DataInitializer dataInitializer() {
        DataInitializer dataInitializer = new DataInitializer();
        dataInitializer.setProductInitializer(productInitializer());
        dataInitializer.setProposalInitializer(proposalInitializer());
        dataInitializer.setSellerInitializer(sellerInitializer());
        dataInitializer.setUserInitializer(userInitializer());
        
        return dataInitializer;
    }
    
    @Bean
    public ProductInitializer productInitializer() {
        return new ProductInitializer(productService);
    }
    
    @Bean
    public ProposalInitializer proposalInitializer() {
        return new ProposalInitializer();
    }
    
    @Bean
    public SellerInitializer sellerInitializer() {
        Map<Long, String> map = new HashMap<Long, String>();
        map.put(new Long(1), Sellers.AMAZON);
        map.put(new Long(2), Sellers.SAMSUNG);
        map.put(new Long(3), "Apple");
        
        return new SellerInitializer(sellerService, map);
    }
    
    @Bean
    public UserInitializer userInitializer() {
        return new UserInitializer(userService);
    }
}
