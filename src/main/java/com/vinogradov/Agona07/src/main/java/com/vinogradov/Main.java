package com.vinogradov;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    }
}

/*
Вывелось в Консоль, если запускать просто с Main класса:

Bean name - appConfig; Bean class - com.vinogradov.AppConfig
OrderService constructor now called
Bean name - orderService; Bean class - com.vinogradov.OrderService
ProductService constructor now called
Bean name - productService; Bean class - com.vinogradov.ProductService
UserService constructor now calles
Bean name - userService; Bean class - com.vinogradov.UserService
 */


/*
Выведется в консоль, если запускать через Tomcat:

Bean name - appConfig; Bean class - com.vinogradov.AppConfig$$SpringCGLIB$$0
Bean name - homeController; Bean class - com.vinogradov.HomeController
OrderService constructor now called
Bean name - orderService; Bean class - com.vinogradov.OrderService
ProductService constructor now called
Bean name - productService; Bean class - com.vinogradov.ProductService
UserService constructor now calles
Bean name - userService; Bean class - com.vinogradov.UserService
Bean name - org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration; Bean class - org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration
Bean name - mvcContentNegotiationManager; Bean class - org.springframework.web.accept.ContentNegotiationManager
Bean name - mvcConversionService; Bean class - org.springframework.format.support.DefaultFormattingConversionService
Bean name - mvcResourceUrlProvider; Bean class - org.springframework.web.servlet.resource.ResourceUrlProvider
Bean name - requestMappingHandlerMapping; Bean class - org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping
Bean name - mvcPatternParser; Bean class - org.springframework.web.util.pattern.PathPatternParser
Bean name - mvcUrlPathHelper; Bean class - org.springframework.web.util.UrlPathHelper
Bean name - mvcPathMatcher; Bean class - org.springframework.util.AntPathMatcher
Bean name - viewControllerHandlerMapping; Bean class - org.springframework.beans.factory.support.NullBean
Bean name - beanNameHandlerMapping; Bean class - org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping
Bean name - routerFunctionMapping; Bean class - org.springframework.web.servlet.function.support.RouterFunctionMapping
Bean name - resourceHandlerMapping; Bean class - org.springframework.beans.factory.support.NullBean
Bean name - defaultServletHandlerMapping; Bean class - org.springframework.beans.factory.support.NullBean
Bean name - mvcValidator; Bean class - org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport$NoOpValidator
Bean name - requestMappingHandlerAdapter; Bean class - org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter
Bean name - handlerFunctionAdapter; Bean class - org.springframework.web.servlet.function.support.HandlerFunctionAdapter
Bean name - mvcUriComponentsContributor; Bean class - org.springframework.web.method.support.CompositeUriComponentsContributor
Bean name - httpRequestHandlerAdapter; Bean class - org.springframework.web.servlet.mvc.HttpRequestHandlerAdapter
Bean name - simpleControllerHandlerAdapter; Bean class - org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter
Bean name - handlerExceptionResolver; Bean class - org.springframework.web.servlet.handler.HandlerExceptionResolverComposite
Bean name - mvcViewResolver; Bean class - org.springframework.web.servlet.view.ViewResolverComposite
Bean name - localeResolver; Bean class - org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver
Bean name - themeResolver; Bean class - org.springframework.web.servlet.theme.FixedThemeResolver
Bean name - flashMapManager; Bean class - org.springframework.web.servlet.support.SessionFlashMapManager
Bean name - viewNameTranslator; Bean class - org.springframework.web.servlet.view.DefaultRequestToViewNameTranslator
 */