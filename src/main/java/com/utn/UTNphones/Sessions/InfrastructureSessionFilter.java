package com.utn.UTNphones.Sessions;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private SessionManager sessionManager;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        String sessionToken= httpServletRequest.getHeader("Authorization");
        Session session = sessionManager.getSession(sessionToken);
        if (session!=null && "infrastructure".equals(session.getLoggedUser().getType())){
            filterChain.doFilter(httpServletRequest,httpServletResponse);
        }else{
            httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());
        }
    }
}
