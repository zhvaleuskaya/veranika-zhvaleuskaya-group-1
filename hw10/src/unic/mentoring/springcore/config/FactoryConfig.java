package unic.mentoring.springcore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import unic.mentoring.springcore.repository.factory.UserRepositoryFactory;

@Configuration
public class FactoryConfig {
    
    @Bean
    public UserRepositoryFactory userRepositoryFactory() {
        return new UserRepositoryFactory();
    }
}
