package com.gcu.baima.config;

import com.gcu.baima.annotation.CurrentUserId;
import com.gcu.baima.exception.BaimaException;
import com.gcu.baima.utils.JwtHelper;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author xiu
 * @create 2023-05-13 11:49
 */
//@Component
public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CurrentUserId.class) &&
                parameter.getParameterType().isAssignableFrom(String.class);
    }

    @Override
    public String resolveArgument(MethodParameter parameter, ModelAndViewContainer container,
                                  NativeWebRequest request, WebDataBinderFactory factory) {
        HttpServletRequest httpServletRequest = request.getNativeRequest(HttpServletRequest.class);
        String token = httpServletRequest.getHeader("Authorization");
        if (token == null) {
            throw new BaimaException(201, "用户未登录或token已经过期");
        }
        return JwtHelper.getUserId(token);
    }
}
