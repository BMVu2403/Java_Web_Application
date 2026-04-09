package org.example.SS02.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() { return null; }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { WebConfig.class }; // Trỏ đến file cấu hình dưới đây
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" }; // Lắng nghe mọi request thay vì /api/*
    }
}