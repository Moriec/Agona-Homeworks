package org.example.loggingstarter;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.FileAppender;
import jakarta.annotation.PostConstruct;
import org.example.loggingstarter.aop.ControllerLoggingAspect;
import org.example.loggingstarter.aop.RepositoryLoggingAspect;
import org.example.loggingstarter.aop.ServiceLoggingAspect;
import org.example.loggingstarter.exception.GlobalExceptionHandler;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@EnableConfigurationProperties(LoggingStarterProperties.class)
@ConditionalOnProperty(prefix = "logging.starter", name = "enabled", havingValue = "true", matchIfMissing = true)
public class LoggingStarterAutoConfiguration {

    @Bean
    public ControllerLoggingAspect controllerLoggingAspect() {
        return new ControllerLoggingAspect();
    }

    @Bean
    public ServiceLoggingAspect serviceLoggingAspect() {
        return new ServiceLoggingAspect();
    }

    @Bean
    public RepositoryLoggingAspect repositoryLoggingAspect() {
        return new RepositoryLoggingAspect();
    }

    @Bean
    public LogbackConfigurer logbackConfigurer(LoggingStarterProperties props) {
        return new LogbackConfigurer(props);
    }

    @Bean
    public GlobalExceptionHandler globalExceptionHandler() {
        return new GlobalExceptionHandler();
    }

    public static class LogbackConfigurer {

        private final LoggingStarterProperties props;

        public LogbackConfigurer(LoggingStarterProperties props) {
            this.props = props;
        }

        @PostConstruct
        public void configure() {
            LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();

            PatternLayoutEncoder encoder = new PatternLayoutEncoder();
            encoder.setContext(context);
            encoder.setPattern(props.getPattern());
            encoder.start();

            Appender<ILoggingEvent> appender;
            if (props.getDestination() == LoggingStarterProperties.Destination.console) {
                ConsoleAppender<ILoggingEvent> console = new ConsoleAppender<>();
                console.setEncoder(encoder);
                appender = console;
            } else {
                FileAppender<ILoggingEvent> file = new FileAppender<>();
                file.setEncoder(encoder);
                file.setFile(props.getFileName());
                appender = file;
            }

            appender.setContext(context);
            appender.start();

            Logger root = context.getLogger(Logger.ROOT_LOGGER_NAME);
            root.addAppender(appender);
        }
    }
}
