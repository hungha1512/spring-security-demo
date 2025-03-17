package vn.aptech.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin")
@Controller
public class HomeController {

    @GetMapping
    public String index() {
        return "index";
    }
}
