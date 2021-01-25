package com.revature.personalfinance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication @EnableJpaRepositories
public class PersonalFinanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonalFinanceApplication.class, args);
    }

//    @Configuration
//    @EnableWebMvc
//    public class WebConfig implements WebMvcConfigurer {
//
//        @Override
//        public void addCorsMappings(CorsRegistry registry) {
//
//            registry.addMapping("/account")
//                .allowedOrigins("https://localhost:4200")
//                .allowedMethods("PUT")
//                //.allowedHeaders("header1", "header2", "header3")
//                //.exposedHeaders("header1", "header2")
//                .allowCredentials(true).maxAge(3600);
//
//            // Add more mappings...
//        }
//    }

}
