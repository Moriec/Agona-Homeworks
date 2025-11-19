package org.example.agona09;

import org.example.agona09.config.AppConfig;
import org.example.agona09.model.*;
import org.example.agona09.repository.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.util.List;

public class App {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class)) {
            UserRepository users = ctx.getBean(UserRepository.class);
            ProfileRepository profiles = ctx.getBean(ProfileRepository.class);
            CategoryRepository categories = ctx.getBean(CategoryRepository.class);
            ProductRepository products = ctx.getBean(ProductRepository.class);
            OrderRepository orders = ctx.getBean(OrderRepository.class);
            OrderItemRepository items = ctx.getBean(OrderItemRepository.class);

            User u = new User();
            u.setEmail("demo@example.com");
            u.setName("Demo");
            u = users.save(u);

            Profile p = new Profile();
            p.setUser(u);
            p.setPhone("+1000000");
            p = profiles.save(p);

            Category c1 = new Category();
            c1.setName("Books");
            Category c2 = new Category();
            c2.setName("Tech");
            c1 = categories.save(c1);
            c2 = categories.save(c2);

            Product pr = new Product();
            pr.setName("Clean Architecture");
            pr.setPrice(new BigDecimal("120.00"));
            pr.getCategories().addAll(List.of(c1, c2));
            pr = products.save(pr);

            Order o = new Order();
            o.setUser(u);
            o = orders.save(o);

            OrderItem oi = new OrderItem();
            oi.setOrder(o);
            oi.setProduct(pr);
            oi.setQuantity(1);
            items.save(oi);

            System.out.println("Users: " + users.count());
            System.out.println("Profiles: " + profiles.count());
            System.out.println("Categories: " + categories.count());
            System.out.println("Products: " + products.count());
            System.out.println("Orders: " + orders.count());
            System.out.println("OrderItems: " + items.count());
            System.out.println("Product categories size: " + products.findById(pr.getId()).orElseThrow().getCategories().size());
        }
    }
}
