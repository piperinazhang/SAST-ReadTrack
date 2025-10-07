package com.sast.sastreadtrack.filter;

import com.sast.sastreadtrack.util.JwtUtil;
import io.jsonwebtoken.JwtException;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String authHeader = httpRequest.getHeader("Authorization");

        // 登录接口不拦截
        String path = httpRequest.getRequestURI();
        if (path.startsWith("/api/user/login") || path.startsWith("/api/user/register")) {
            chain.doFilter(request, response);
            return;
        }

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.getWriter().write("缺少或无效的 Token");
            return;
        }

        String token = authHeader.substring(7);
        try {
            if (!JwtUtil.validateToken(token)) {
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                httpResponse.getWriter().write("Token 无效或已过期");
                return;
            }
            // ✅ 验证通过，放行
            chain.doFilter(request, response);

        } catch (JwtException e) {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.getWriter().write("Token 校验失败");
        }
    }
}
