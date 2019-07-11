package bcntec.training.springboot.sso.server;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import bcntec.training.springboot.sso.server.domain.User;

@RestController
@RequestMapping("/private/api/v1/users")
public class UserController {
	
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value="/me", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody User getCurrentUser() throws Exception {
		User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return principal; //this.service.getUser(principal.getId());
	}
	
}
