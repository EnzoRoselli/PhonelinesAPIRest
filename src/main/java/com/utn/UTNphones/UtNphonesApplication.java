package com.utn.UTNphones;

import com.utn.UTNphones.Domains.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class UtNphonesApplication {

    public static void main(String[] args) {

       SpringApplication.run(UtNphonesApplication.class, args);

//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
//        LocalDateTime now = LocalDateTime.now();
//        System.out.println(dtf.format(now));
    }

}
