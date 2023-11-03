package it.corso.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(filterName = "log", urlPatterns = {"/home","/post","/postVisualizer"})
public class LogFilter extends HttpFilter implements Filter {

    private static final long serialVersionUID = 7171867327345436852L;

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        Cookie[] cookies = req.getCookies();
        boolean cookiePresent = false;
        for (Cookie c : cookies) {
            if (c.getName().equals("log")) {
                cookiePresent = true;
                break;
            }
        }

        if (cookiePresent) {
            chain.doFilter(request, response);
        } else {
            HttpServletResponse res = (HttpServletResponse) response;
            res.sendRedirect("login");
        }
    }
}
