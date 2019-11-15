package bcntec.training.springboot.sso.server;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class ServerController {

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


    @GetMapping("/my-login")
    public String myLogin() {
        return "my-login";
    }

}
