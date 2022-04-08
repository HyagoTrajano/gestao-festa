package com.gestaofesta.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    
    @RequestMapping("/home")
    public String form(){
        return "index";
    }
}
