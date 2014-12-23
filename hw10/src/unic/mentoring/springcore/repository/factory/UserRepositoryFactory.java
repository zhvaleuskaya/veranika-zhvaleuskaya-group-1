package unic.mentoring.springcore.repository.factory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import unic.mentoring.springcore.data.User;
import unic.mentoring.springcore.repository.UserRepository;
import unic.mentoring.springcore.repository.map.AbstractMapRepository;

@Component
public final class UserRepositoryFactory
{
	@Bean
	public UserRepository createUserRepository()
	{
		return new UserMapRepository();
	}

	private class UserMapRepository extends AbstractMapRepository<User> implements UserRepository
	{
		@Override
		public User getUserById(Long id)
		{
			return get(id);
		}

		@Override
		public Long createUser(User user)
		{
			return create(user);
		}

		@Override
		public void updateUser(User user)
		{
			update(user);
		}

		@Override
		public List<User> getUsers()
		{
			return new ArrayList<User>(register.values());
		}
	}
}