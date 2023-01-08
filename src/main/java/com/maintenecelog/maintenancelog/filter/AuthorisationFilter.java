package com.maintenecelog.maintenancelog.filter;

import com.maintenecelog.maintenancelog.model.Token;
import com.maintenecelog.maintenancelog.service.TokenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class AuthorisationFilter implements Filter {

    private final TokenServiceImpl tokenService;

    @Autowired
    public AuthorisationFilter(TokenServiceImpl tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String auth = httpRequest.getHeader("auth").toString();
        Token token = null;
        try {
            token = tokenService.getToken(auth);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        //TODO Finish filter cases
        if(auth.equals(token.getToken())){
            return;
        }

    }
}
