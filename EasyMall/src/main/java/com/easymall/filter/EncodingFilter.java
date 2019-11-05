package com.easymall.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@WebFilter(urlPatterns = "/*", initParams = {@WebInitParam(name = "encode", value = "utf-8")})
//增加初始化变量，通过FilterConfig对象获取
public class EncodingFilter implements Filter {
    private FilterConfig config;
    private String encode;

    public void init(FilterConfig config) throws ServletException {
        this.config = config;
        this.encode = config.getInitParameter("encode");
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        String url = request.getRequestURL().toString();
        // 排除css样式或图片资源
        if (!url.matches(".*/((css)|(img))/.*")) {
            resp.setContentType("text/html;charset=" + encode);
        }
        req.setCharacterEncoding(encode);
        chain.doFilter(new MyHttpServletRequest(request), resp);
    }

    class MyHttpServletRequest extends HttpServletRequestWrapper {
        private HttpServletRequest request;
        private boolean isEncoded = false;

        public MyHttpServletRequest(HttpServletRequest request) {
            super(request);
            this.request = request;
        }

        @Override
        public Map<String, String[]> getParameterMap() {
            try {
                if ("post".equalsIgnoreCase(request.getMethod())) {
                    request.setCharacterEncoding(encode);
                    return request.getParameterMap();
                } else if ("get".equalsIgnoreCase(request.getMethod())) {
                    Map<String, String[]> map = new HashMap<>(request.getParameterMap());
                    if (!isEncoded) {
                        for (Map.Entry<String, String[]> entry : map.entrySet()) {
                            String[] value = entry.getValue();
                            for (int i = 0; i < value.length; i++) {
                                value[i] = new String(value[i].getBytes("iso8859-1"), encode);
                            }
                        }
                        isEncoded = true;

                    }
                    return map;
                } else {
                    return request.getParameterMap();
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        public String getParameter(String name) {
            String[] value = getParameterValues(name);
            return value == null ? null : value[0];

        }


        @Override
        public String[] getParameterValues(String name) {
            return getParameterMap().get(name);
        }
    }


}
