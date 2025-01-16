package kr.hhplus.be.server.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class LoggingFilter implements Filter {
    private final Logger log = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
        log.info("[LoggingFilter] init");
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        log.info("[LoggingFilter] REQUEST URI: {}", req.getRequestURI());
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        log.info("[LoggingFilter] destroy");
    }
}
