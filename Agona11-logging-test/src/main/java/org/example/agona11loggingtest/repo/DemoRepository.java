package org.example.agona11loggingtest.repo;

import org.springframework.stereotype.Repository;

@Repository
public class DemoRepository {
    public int len(String s) {
        try { Thread.sleep(25); } catch (InterruptedException ignored) {}
        return s == null ? 0 : s.length();
    }
}
