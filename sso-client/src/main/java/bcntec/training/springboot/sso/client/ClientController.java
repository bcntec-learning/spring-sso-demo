package bcntec.training.springboot.sso.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class ClientController {

    @ResponseBody
    @GetMapping("/session/me")
    public Principal user(Principal principal) {
        return principal;
    }

    @GetMapping("/")
    public String home(Principal principal) {
        return "index";
    }

    @RequestMapping("/secured-page")
    public String securedPage(Model model, Principal principal) {
        return "secured-page";
    }


}
