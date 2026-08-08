package org.brinka.brinkaapi.infra.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Component
public class SecurityExceptionHandlerDelegate implements AuthenticationEntryPoint, AccessDeniedHandler {
    @Qualifier("handlerExceptionResolver")
    private final HandlerExceptionResolver resolver;

    public SecurityExceptionHandlerDelegate(
            @Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException ex) {
        resolver.resolveException(request, response, null, ex);

    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException ex) {
        resolver.resolveException(request, response, null, ex);
    }
}
