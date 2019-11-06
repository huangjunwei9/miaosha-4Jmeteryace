package com.imooc.miaosha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class MainApplication {

    public static void main( String[] args ) throws Exception {
        System.out.println( "Hello World!" );
        SpringApplication.run(MainApplication.class,args);
    }



}


//@SpringBootApplication
//public class MainApplication extends SpringBootServletInitializer {
//
//    public static void main( String[] args ) throws Exception {
//        System.out.println( "Hello World!" );
//        SpringApplication.run(MainApplication.class,args);
//    }
//
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(MainApplication.class);
//    }
//
//}
