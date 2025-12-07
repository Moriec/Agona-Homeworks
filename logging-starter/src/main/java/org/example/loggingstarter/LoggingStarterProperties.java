package org.example.loggingstarter;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "logging.starter")
public class LoggingStarterProperties {
    public enum Destination { console, file }

    private boolean enabled = true;
    private Destination destination = Destination.console;
    private String fileName = "logs/app.log";
    private String module = "app";
    private String pattern = "%d{yyyy-MM-dd HH:mm:ss.SSS} %level  --- %X{module}  %logger : %msg %X{execTime}%n";

    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }
    public Destination getDestination() { return destination; }
    public void setDestination(Destination destination) { this.destination = destination; }
    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }
    public String getModule() { return module; }
    public void setModule(String module) { this.module = module; }
    public String getPattern() { return pattern; }
    public void setPattern(String pattern) { this.pattern = pattern; }
}
