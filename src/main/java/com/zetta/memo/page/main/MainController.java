package com.zetta.memo.page.main;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    private MainService mainService;

    MainController() {
        MainService mainService = new MainService();
        this.mainService = mainService;
    }

    @GetMapping("/hello")
    public String getHello(String name) {
        return mainService.getSomething();
    }
}
