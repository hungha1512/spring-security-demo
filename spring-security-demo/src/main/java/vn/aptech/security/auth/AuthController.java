package vn.aptech.security.auth;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.aptech.security.auth.payload.LoginRequest;

@RequestMapping("/auth")
@Controller
public class AuthController {

    @GetMapping("/login")
    public String login(ModelMap model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }
}
