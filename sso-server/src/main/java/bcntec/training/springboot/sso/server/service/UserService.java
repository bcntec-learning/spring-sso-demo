package bcntec.training.springboot.sso.server.service;

import bcntec.training.springboot.sso.server.domain.User;

public interface UserService {
	
	User getUser(String id);

}
