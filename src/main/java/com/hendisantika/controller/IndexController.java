package com.hendisantika.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ThreadLocalRandom;

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

    @GetMapping("/api1")
    public String api1() {
        addLatency(0, 20);
        return apiResponse(1);
    }

    @GetMapping("/api2")
    public String api2() {
        addLatency(10, 100);
        return apiResponse(2);
    }

    @RequestMapping("/api3")
    public String api3() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://application2:8080/", String.class);
        return apiResponse(3) + ". Response from outbound request was " + responseEntity.getStatusCode();
    }

    private String apiResponse(int apiNumber) {
        return String.format("API %s response from %s", apiNumber, applicationName);
    }

    private void addLatency(int minimumMs, int maximumMs) {
        long sleepDuration = ThreadLocalRandom.current().nextInt(minimumMs, maximumMs + 1);
        try {
            Thread.sleep(sleepDuration);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
