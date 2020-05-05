package prt.navitruck.back.app.config;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

public class PageableConfiguration extends WebMvcConfigurerAdapter {

//    static final PageRequest DEFAULT_PAGE_REQUEST = new PageRequest(1, 20, Sort.Direction.DESC);
//
//    @Override
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
//        PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
//        resolver.setFallbackPageable(DEFAULT_PAGE_REQUEST);
//        argumentResolvers.add(resolver);
//        super.addArgumentResolvers(argumentResolvers);
//    }
}