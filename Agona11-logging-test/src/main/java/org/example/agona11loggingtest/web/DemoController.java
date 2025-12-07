package org.example.agona11loggingtest.web;

import org.example.agona11loggingtest.service.DemoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    private final DemoService service;
    public DemoController(DemoService service) { this.service = service; }

    @GetMapping("/hello")
    public String hello(@RequestParam("name") String name) {
        return service.process(name);
    }
}
