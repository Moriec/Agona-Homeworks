package org.example.agona11loggingtest.service;

import org.example.agona11loggingtest.repo.DemoRepository;
import org.springframework.stereotype.Service;

@Service
public class DemoService {
    private final DemoRepository repo;
    public DemoService(DemoRepository repo) { this.repo = repo; }

    public String process(String name) {
        int len = repo.len(name);
        return String.format("HELLO %s (len=%d)", name.toUpperCase(), len);
    }
}
