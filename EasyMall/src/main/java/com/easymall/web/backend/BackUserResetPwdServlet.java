package com.easymall.web.backend;

import com.easymall.factory.BasicFactory;
import com.easymall.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servlet/BackUserResetPwdServlet")
public class BackUserResetPwdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        UserService service = BasicFactory.getFactory().getInstance(UserService.class);
        boolean success = service.resetPwd(id);
        response.getWriter().write(success + "");
    }
}