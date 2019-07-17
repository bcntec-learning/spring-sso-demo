package bcntec.training.springboot.sso.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bcntec.training.springboot.sso.server.domain.User;
import bcntec.training.springboot.sso.server.exception.NotFoundException;
import bcntec.training.springboot.sso.server.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	@Override
	public User getUser(String id) {
		return this.repository.findById(id).orElseThrow(() -> new NotFoundException());
	}
}
