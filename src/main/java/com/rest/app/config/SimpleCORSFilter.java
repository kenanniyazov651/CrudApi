
package com.rest.app.config;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SimpleCORSFilter implements Filter {

    private static final Logger logger = LogManager.getLogger(SimpleCORSFilter.class);


    public SimpleCORSFilter() {
        logger.info("SimpleCORSFilter init");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

//        response.setHeader("Access-Control-Allow-Origin", request.getHeader("origin")  );
        response.setHeader("Access-Control-Allow-Origin","*" );
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE,PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type ,Accept, X-Requested-With,Authorization,  remember-me");
//        response.setHeader("Access-Control-Request-Headers","Authorization");
//        response.setHeader("Authorization ","Authorization");
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
//
//        Map<String, String> map = new HashMap<String, String>();
//
//        Enumeration headerNames = request.getHeaderNames();
//
//        while (headerNames.hasMoreElements()) {
//            String key = (String) headerNames.nextElement();
//            if (key.equalsIgnoreCase("Authorization"))
//            {
//                System.out.println("666666666666666666666 "+key.toString());
//
//            }
//            String value = request.getHeader(key);
//            System.out.println("key : "+key+"; value : "+value);
//        }
//        String authHeader = request.getHeader("Authorization");
//        System.out.println("999999999999"+authHeader);
        chain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }


}
