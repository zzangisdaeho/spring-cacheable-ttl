package com.example.springcacheableredisttl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class SpringCacheableRedisTtlApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCacheableRedisTtlApplication.class, args);
    }

}
