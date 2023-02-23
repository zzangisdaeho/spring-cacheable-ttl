package com.example.springcacheableredisttl.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@EnableScheduling
public class TestService {

    private int num1 = 0;
    private int num2 = 0;

    @Cacheable(value = "num", key = "#numnum")
    public int addAndReturn(int numnum){
        if(numnum == 1){
            log.info("method working. numnum = 1");
            return ++num1;
        }else if(numnum == 2){
            log.info("method working. numnum = 2");
            return ++num2;
        }else{
            throw new IllegalArgumentException("1이랑 2만 가능험");
        }
    }

    @Scheduled(cron = "0/10 * * ? * ?")
    @CacheEvict(value = "num", allEntries = true)
    public void EvictNumCache(){
        log.info("cache name : Num  cleared");
    }
}
