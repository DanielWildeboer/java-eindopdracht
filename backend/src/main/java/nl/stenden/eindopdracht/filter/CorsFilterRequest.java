package nl.stenden.eindopdracht.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilterRequest implements Filter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public CorsFilterRequest() {
        logger.info("CorsFilter init");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("initialisingFilter");
    }

    /**
     * In this method we set the headers to allow cross domain requests and then tell the filterChain to continue normally after we do so.
     * @param servletRequest the received request
     * @param servletResponse the response we are sending back
     * @param filterChain chain of filters to walk through
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // Cast request/response to the HTTP equivalent
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with, Authorization, Content-Type");
        response.setHeader("Access-Control-Max-Age", "3600");

        if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}