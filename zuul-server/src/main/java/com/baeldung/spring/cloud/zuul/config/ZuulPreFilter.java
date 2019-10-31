package com.baeldung.spring.cloud.zuul.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

//https://github.com/spring-cloud/spring-cloud-netflix/issues/904
//https://dzone.com/articles/dynamic-routing-through-zuul-with-rest-api-spring
//https://serverfault.com/questions/557233/apache-proxy-http-redirect-to-ip-and-set-hostname
//https://cloud.spring.io/spring-cloud-netflix/reference/html/#router-and-filter-zuul

public class ZuulPreFilter extends ZuulFilter {

    public static final String HEADER_HOST = "Host";
    public static final String HEADER_X_FORWARDED_HOST = "X-Forwarded-Host";

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        return ctx.getRequest().getHeaders(HEADER_HOST).hasMoreElements();
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        String host = ctx.getRequest().getHeaders(HEADER_HOST).nextElement();
        ctx.getZuulRequestHeaders().put(HEADER_HOST, host);

        return null;
    }
}
