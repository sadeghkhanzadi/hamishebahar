package com.hamishebahar.security.config;

import com.hamishebahar.security.enums.Authority;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    DefaultRedirectStrategy redirect = new DefaultRedirectStrategy();


    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {

        if (authentication.getAuthorities().contains(Authority.OP_ACCESS_ADMIN)) {
            redirect.sendRedirect(httpServletRequest, httpServletResponse, "https://hamishebahar2.liara.run");// /admin
        } else if (authentication.getAuthorities().contains(Authority.OP_ACCESS_USER)) {
            redirect.sendRedirect(httpServletRequest, httpServletResponse, "https://hamishebahar2.liara.run");// /user
        } else
            redirect.sendRedirect(httpServletRequest, httpServletResponse, "https://hamishebahar2.liara.run");// /
    }
}
