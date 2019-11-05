package com.easymall.filter;

import com.easymall.pojo.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter("/*")
public class PrivFilter implements Filter {
    private List<String> userlist = new ArrayList<>();
    private List<String> adminlist = new ArrayList<>();

    @Override
    public void init(FilterConfig fc) throws ServletException {
        String userpath = fc.getServletContext().getRealPath("/WEB-INF/user.txt");
        String adminpath = fc.getServletContext().getRealPath("/WEB-INF/admin.txt");
        try (BufferedReader in1 = new BufferedReader(new FileReader(userpath));
             BufferedReader in2 = new BufferedReader(new FileReader(adminpath))) {
            String line1 = null;
            while ((line1 = in1.readLine()) != null) {
                userlist.add(line1);
            }
            String line2 = null;
            while ((line2 = in2.readLine()) != null) {
                adminlist.add(line2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String uri = req.getRequestURI();
        if (userlist.contains(uri) || adminlist.contains(uri)) {
            User user = (User) req.getSession().getAttribute("user");
            if (user == null) {
                req.getRequestDispatcher("/login.jsp").forward(request, response);
            } else {
                if (("user".equals(user.getRole()) && userlist.contains(uri))) {
                    filterChain.doFilter(request, response);
                } else if (("admin".equals(user.getRole()) && adminlist.contains(uri))) {
                    filterChain.doFilter(request, response);
                } else {
                    response.getWriter().write("您无权访问该资源");
                }
            }
        } else {
            filterChain.doFilter(req, response);
        }
    }

    @Override
    public void destroy() {

    }
}