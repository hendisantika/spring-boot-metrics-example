package com.hendisantika.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-metrics-example
 * User: TOSHIBA
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 9/4/2021
 * Time: 9:33 AM
 * <p>
 * To change this template use File | Settings | File Templates.
 */
@RestController
public class IndexController {
    @Value("${applicationName}")
    private String applicationName;

    @Autowired
    private RestTemplate restTemplate;

}
