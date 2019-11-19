package com.autogram.filter;

import com.autogram.model.entity.User;
import com.autogram.model.orm.repository.UserRepository;
import com.autogram.model.orm.specification.user.UserByIdSpecification;
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

        Pattern login = Pattern.compile("^/login*$");
        Pattern signup = Pattern.compile("^/sign-up*$");
        Pattern profile = Pattern.compile("^/profile*$");
        Pattern patternSubscriber = Pattern.compile("^/subscriber*$");
        Pattern profileSubscription = Pattern.compile("^/subscriprion*$");
        Pattern newPostEdit = Pattern.compile("^/new-post*$");
        Pattern profileEdit = Pattern.compile("^/edit*$");
        Pattern tag = Pattern.compile("^/tag*$");
        Pattern post = Pattern.compile("^/post*$");
        Pattern like = Pattern.compile("^/like*$");
        Pattern feed = Pattern.compile("^/feed*$");
        Pattern userFinder = Pattern.compile("^/user*$");
        String uri = req.getRequestURI();
        if (login.matcher(uri).matches() || signup.matcher(uri).matches()) {
            if (req.getSession().getAttribute("id") != null
                    && req.getSession().getAttribute("token") != null) {
                resp.sendRedirect("http://localhost:8080/profile");
                return;
            }
        } else if (profile.matcher(uri).matches() || patternSubscriber.matcher(uri).matches()
                || profileSubscription.matcher(uri).matches() || newPostEdit.matcher(uri).matches()
                || newPostEdit.matcher(uri).matches()
                || profileEdit.matcher(uri).matches()
                || tag.matcher(uri).matches()
                || post.matcher(uri).matches()
                || like.matcher(uri).matches()
                || feed.matcher(uri).matches()
                || userFinder.matcher(uri).matches()) {
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
                if (id == null || token == null) {
                    resp.sendRedirect("http://localhost:8080/login");
                    return;
                } else {
                    Optional<User> user = new UserRepository(DBConnection.getInstance())
                            .findOne(new UserByIdSpecification(Integer.valueOf(id), token));
                    if (user.isPresent()) {
                        req.getSession().setMaxInactiveInterval(-1);
                        req.getSession().setAttribute("id", user.get().getId());
                        req.getSession().setAttribute("token", user.get().getToken());
                        Cookie cookieToken = new Cookie("token", user.get().getToken());
                        Cookie cookieId = new Cookie("id", String.valueOf(user.get().getId()));
                        cookieToken.setMaxAge(2592000);
                        cookieId.setMaxAge(2592000);
                        resp.addCookie(cookieId);
                        resp.addCookie(cookieToken);
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
                        .findAll(new UserByIdSpecification((Integer) req.getSession().getAttribute("id"),
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
