package com.utn.UTNphones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class UtNphonesApplication {

    public static void main(String[] args) {
       SpringApplication.run(UtNphonesApplication.class, args);

    }


}
