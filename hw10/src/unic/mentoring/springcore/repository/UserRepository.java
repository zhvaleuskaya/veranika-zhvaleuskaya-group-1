package unic.mentoring.springcore.repository;

import java.util.List;

import unic.mentoring.springcore.data.User;

public interface UserRepository
{
	User getUserById(Long id);
	Long createUser(User user);
	void updateUser(User user);
	List<User> getUsers();
}