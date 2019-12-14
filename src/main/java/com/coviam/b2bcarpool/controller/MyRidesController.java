package com.coviam.b2bcarpool.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("api/myrides")
public class MyRidesController {

    @GetMapping("/")
    public String getMyRides() {
        return getUpcomingRides();
    }

    @GetMapping("/upcoming")
    public String getUpcomingRides() {
        return "Hi!";
    }

    @GetMapping("/history")
    public String getHistoryRides() {
        return "Hello!";
    }
}
