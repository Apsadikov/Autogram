package com.autogram.filter;

import com.autogram.model.entity.User;
import com.autogram.model.orm.repository.UserRepository;
import com.autogram.model.orm.specification.user.UserIdSpecification;
import com.autogram.util.DBConnection;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        Pattern patternLogin = Pattern.compile("^/login*$");
        Pattern patternSignup = Pattern.compile("^/sign-up*$");
        Pattern patternProfile = Pattern.compile("^/profile*$");
        String uri = req.getRequestURI();
        if (patternLogin.matcher(uri).matches() || patternSignup.matcher(uri).matches()) {
            if (req.getSession().getAttribute("id") != null
                    && req.getSession().getAttribute("token") != null) {
                resp.sendRedirect("http://localhost:8080/profile");
                return;
            }
        } else if (patternProfile.matcher(uri).matches()) {
            if (req.getSession().getAttribute("id") == null
                    && req.getSession().getAttribute("token") == null) {
                String id = null;
                String token = null;
                for (Cookie cookie : req.getCookies()) {
                    if (cookie.getName().equals("id")) {
                        id = cookie.getValue();
                    }
                    if (cookie.getName().equals("token")) {
                        token = cookie.getValue();
                    }
                }
                if (id == null && token == null) {
                    resp.sendRedirect("http://localhost:8080/login");
                    return;
                } else {
                    Optional<List<User>> user = new UserRepository(DBConnection.getInstance())
                            .query(new UserIdSpecification(Integer.valueOf(id), token));
                    if (user.isPresent() && user.get().size() != 0) {
                        filterChain.doFilter(req, resp);
                        return;
                    }
                    req.getSession().invalidate();
                    Cookie cookieId = new Cookie("token", "");
                    Cookie cookieToken = new Cookie("id", "");
                    cookieId.setMaxAge(0);
                    cookieToken.setMaxAge(0);
                    resp.addCookie(cookieId);
                    resp.addCookie(cookieToken);
                    resp.sendRedirect("http://localhost:8080/login");
                    filterChain.doFilter(req, resp);
                    return;
                }
            } else {
                Optional<List<User>> user = new UserRepository(DBConnection.getInstance())
                        .query(new UserIdSpecification((Integer) req.getSession().getAttribute("id"),
                                (String) req.getSession().getAttribute("token")));
                if (user.isPresent() && user.get().size() != 0) {
                    filterChain.doFilter(req, resp);
                    return;
                }
                req.getSession().invalidate();
                Cookie cookieId = new Cookie("token", "");
                Cookie cookieToken = new Cookie("id", "");
                cookieId.setMaxAge(0);
                cookieToken.setMaxAge(0);
                resp.addCookie(cookieId);
                resp.addCookie(cookieToken);
                resp.sendRedirect("http://localhost:8080/login");
                return;
            }
        }
        filterChain.doFilter(req, resp);
    }
}
