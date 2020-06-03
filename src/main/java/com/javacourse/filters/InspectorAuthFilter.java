package com.javacourse.filters;

import com.javacourse.model.entities.Inspector;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "inspector-auth", urlPatterns = "/inspector/*")
public class InspectorAuthFilter implements Filter {
    private static Logger logger = Logger.getLogger(InspectorAuthFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) request).getSession();
        Inspector inspector = (Inspector) session.getAttribute("inspector");
        if (inspector!=null) {
            filterChain.doFilter(request, response);
        } else {
            logger.error("Unauthorized entry to inspector panel from ip:"+request.getRemoteAddr());
            ((HttpServletResponse) response).sendRedirect(((HttpServletRequest) request).getContextPath() + "/");
        }
    }

    @Override
    public void destroy() {

    }
}