package com.utn.UTNphones.sessions;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class InfrastructureSessionFilter extends OncePerRequestFilter {

    @Value("${INFRASTRUCTURE_KEY}")
    private String infrastrucutreKey;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

//        String key = httpServletRequest.getHeader("INFRASTRUCTURE_KEY");

//        if (infrastrucutreKey.equals(key)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
//        } else {
//            httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());
//        }
    }
}
