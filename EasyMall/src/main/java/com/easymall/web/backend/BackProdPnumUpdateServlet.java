package com.easymall.web.backend;

import com.easymall.factory.BasicFactory;
import com.easymall.service.ProdService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servlet/BackProdPnumUpdateServlet")
public class BackProdPnumUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pid = request.getParameter("pid");
        int pnum = Integer.parseInt(request.getParameter("pnum"));
        ProdService service = BasicFactory.getFactory().getInstance(ProdService.class);
        boolean success = service.updatePnum(pid, pnum);
        response.getWriter().write(success + "");
    }
}