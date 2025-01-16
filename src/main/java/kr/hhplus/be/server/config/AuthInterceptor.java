package kr.hhplus.be.server.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        String token = request.getHeader("X-Auth-Token");
        if (token == null || token.isBlank()) {
            response.setStatus(401);
            response.getWriter().write("Unauthorized\n");
            return false;
        }
        return true;
    }
}
