package com.utn.UTNphones;

import com.utn.UTNphones.Domains.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class UtNphonesApplication {

    public static void main(String[] args) {

       SpringApplication.run(UtNphonesApplication.class, args);
      /*  User a=new User();
        a.setPassword("adasdasdas");
        User b=new User();
        b.setPassword("a");
        b.setNonNullValues(a);
        System.out.println(b.toString());*/

    }
}
