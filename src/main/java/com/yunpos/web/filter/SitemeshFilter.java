package com.yunpos.web.filter;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;


public class SitemeshFilter extends ConfigurableSiteMeshFilter {

    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
    	 builder.addDecoratorPath("/*", "/WEB-INF/decorators/main.jsp");
         builder.addDecoratorPath("/login", "/WEB-INF/decorators/default.jsp");
         builder.addDecoratorPath("/register", "/WEB-INF/decorators/default.jsp");
         builder.addDecoratorPath("/forget", "/WEB-INF/decorators/default.jsp");

         builder.addExcludedPath("/static");
         builder.addExcludedPath("/resources");
         builder.addExcludedPath("/css");
         builder.addExcludedPath("/js");
    }
}
