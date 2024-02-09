package com.sdjeans.sdjeans_app.C_app.Interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.sdjeans.sdjeans_app.C_app.Services.LongPollingService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LongPollingInterceptor implements HandlerInterceptor {

    @Autowired
    private LongPollingService longPollingService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // リクエスト前に実行される処理
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) {
        // ハンドラが処理した後に実行される処理
        if (modelAndView != null) {
            modelAndView.addObject("deferredResult", longPollingService.getDeferredResult());
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) {
        // レスポンスが完了した後に実行される処理
    }
}
