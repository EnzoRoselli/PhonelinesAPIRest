package com.utn.UTNphones;

import com.utn.UTNphones.Models.City;
import com.utn.UTNphones.Models.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.support.NullValue;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class UtNphonesApplication {

    public static void main(String[] args) {

SpringApplication.run(UtNphonesApplication.class, args);

    }

}
