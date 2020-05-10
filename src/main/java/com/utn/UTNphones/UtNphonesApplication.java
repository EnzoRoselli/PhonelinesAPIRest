package com.utn.UTNphones;

import com.utn.UTNphones.Models.City;
import com.utn.UTNphones.Models.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.support.NullValue;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@SpringBootApplication
public class UtNphonesApplication {

    public static void main(String[] args) {

SpringApplication.run(UtNphonesApplication.class, args);

    }

}
