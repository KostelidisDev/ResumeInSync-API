package gr.ihu.ict.resumeinsync.api.config.security;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static gr.ihu.ict.resumeinsync.common.util.BooleanUtils.NOT;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CORSFilter implements Filter {

    @Override
    public void doFilter(final ServletRequest servletRequest,
            final ServletResponse servletResponse,
            final FilterChain filterChain)
            throws IOException, ServletException {
        final HttpServletResponse response = (HttpServletResponse) servletResponse;

        final String allowOrigin = "*";
        final String allowMethods = "POST, GET, PUT, DELETE, OPTIONS";
        final String allowHeaders = "Content-Type, X-Requested-With, X-Auth-Token, Authorization, x-ijt";
        final String exposeHeaders = "Location";
        final String maxAge = "3600";
        final String allowCredentials = "true";

        response.setHeader("Access-Control-Allow-Origin", allowOrigin);
        response.setHeader("Access-Control-Allow-Methods", allowMethods);
        response.setHeader("Access-Control-Allow-Headers", allowHeaders);
        response.setHeader("Access-Control-Expose-Headers", exposeHeaders);
        response.setHeader("Access-Control-Max-Age", maxAge);
        response.setHeader("Access-Control-Allow-Credentials", allowCredentials);

        final HttpServletRequest request = (HttpServletRequest) servletRequest;

        if (NOT(("OPTIONS".equalsIgnoreCase(request.getMethod())))) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
