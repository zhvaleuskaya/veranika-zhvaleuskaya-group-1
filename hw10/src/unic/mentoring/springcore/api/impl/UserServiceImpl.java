package unic.mentoring.springcore.api.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import unic.mentoring.springcore.api.UserService;
import unic.mentoring.springcore.data.User;
import unic.mentoring.springcore.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository repository;

    @Override
    public Long registerUser(User user) {
        return repository.createUser(user);
    }

    @Override
    public User getUserById(Long userId) {
        return repository.getUserById(userId);
    }

    @Override
    public void updateUserProfile(User user) {
        repository.updateUser(user);
    }

    @Override
    public List<User> getUsers() {
        return repository.getUsers();
    }
    
    @Autowired
    public void populate(UserRepository repository) {
        this.repository = repository;
    }
}
