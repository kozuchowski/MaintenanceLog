package com.maintenecelog.maintenancelog.filter;

import com.maintenecelog.maintenancelog.model.Token;
import com.maintenecelog.maintenancelog.service.TokenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class AuthorisationFilter extends OncePerRequestFilter {

    private final TokenServiceImpl tokenService;

    @Autowired
    public AuthorisationFilter(TokenServiceImpl tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request)
            throws ServletException {
        String path = request.getRequestURI();
        return "/mainteiners/logIn".equals(path) || "/mainteiners/signIn".equals(path) || "/swagger-ui/index.html".equals(path);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String auth = request.getHeader("auth");
        LocalDateTime now = LocalDateTime.now();
        Token token = null;
        try {
            token = tokenService.getToken(auth);
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        if(token != null && now.isBefore(token.getExpiring())){
            filterChain.doFilter(request, response);
        }
    }

}


