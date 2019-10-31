package com.baeldung.spring.cloud.eureka.client;

import org.springframework.web.bind.annotation.RequestMapping;

public interface GreetingController {
    @RequestMapping("/greeting")
    String greeting();

    @RequestMapping("/greet")
    String greeting(String jsessionid, String cookieVal);
}
