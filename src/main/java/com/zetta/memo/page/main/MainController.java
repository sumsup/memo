package com.zetta.memo.page.main;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/hello")
    public String getHello(String name) {
        return name + "님? hello world!!";
    }
}
