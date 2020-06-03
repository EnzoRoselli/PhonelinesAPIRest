package com.utn.UTNphones.Config;

import com.utn.UTNphones.Sessions.EmployeeSessionFilter;
import com.utn.UTNphones.Sessions.InfrastructureSessionFilter;
import com.utn.UTNphones.Sessions.SessionFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@org.springframework.context.annotation.Configuration
@EnableScheduling
public class configuration {

    @Autowired
    SessionFilter sessionFilter;
    @Autowired
    EmployeeSessionFilter employeeSessionFilter;
    @Autowired
    InfrastructureSessionFilter infrastructureSessionFilter;


    @Bean
    public FilterRegistrationBean clientFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(sessionFilter);
        registration.addUrlPatterns("/client/*");
        return registration;
    }
    @Bean
    public FilterRegistrationBean employeeFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(employeeSessionFilter);
        registration.addUrlPatterns("/employee/*");
        return registration;
    }
    @Bean
    public FilterRegistrationBean infrastructureFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(infrastructureSessionFilter);
        registration.addUrlPatterns("/infrastructure/*");
        return registration;
    }


}
