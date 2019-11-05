package com.easymall.web;

import com.easymall.factory.BasicFactory;
import com.easymall.service.OrderService;
import com.easymall.utils.PayUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servlet/CallBackServlet")
public class CallBackServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String p1_MerId = request.getParameter("p1_MerId");
        String r0_Cmd = request.getParameter("r0_Cmd");
        String r1_Code = request.getParameter("r1_Code");
        String r2_TrxId = request.getParameter("r2_TrxId");
        String r3_Amt = request.getParameter("r3_Amt");
        String r4_Cur = request.getParameter("r4_Cur");
        String r5_Pid = request.getParameter("r5_Pid");
        String r6_Order = request.getParameter("r6_Order");
        String r7_Uid = request.getParameter("r7_Uid");
        String r8_MP = request.getParameter("r8_MP");
        String r9_BType = request.getParameter("r9_BType");
        String rb_BankId = request.getParameter("rb_BankId");
        String ro_BankOrderId = request.getParameter("ro_BankOrderId");
        String rp_PayDate = request.getParameter("rp_PayDate");
        String rq_CardNo = request.getParameter("rq_CardNo");
        String ru_Trxtime = request.getParameter("ru_Trxtime");
        // 身份校验 --- 判断是不是支付公司通知你
        String hmac = request.getParameter("hmac");
        String keyValue = PayUtils.getValueFromPro("keyValue");

        // 自己对上面数据进行加密 --- 比较支付公司发过来hamc
        boolean isValid = PayUtils.verifyCallback(hmac, p1_MerId, r0_Cmd,
                r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid,
                r8_MP, r9_BType, keyValue);
        if (isValid) {
            // 响应数据有效
            if ("1".equals(r9_BType)) {
                // 浏览器重定向
                response.getWriter().println("<h1>付款成功！等待商城进一步操作！等待收货...</h1>");
                OrderService orderService = BasicFactory.getFactory().getInstance(OrderService.class);
                orderService.updatePaystateByOid(r6_Order, 1);
            } else if ("2".equals(r9_BType)) {
                if ("1".equals(r1_Code)) {
                    // 服务器点对点 --- 支付公司通知你
                    System.out.println("付款成功！");
                    OrderService orderService = BasicFactory.getFactory().getInstance(OrderService.class);
                    orderService.updatePaystateByOid(r6_Order, 1);
                    // 回复支付公司
                    response.getWriter().print("success");
                }
            }
        } else {
            // 数据无效
            System.out.println("数据被篡改！");
        }
    }
}
