package com.yunpos.application;

import com.yunpos.Application;
import com.yunpos.model.FilterDifinition;
import com.yunpos.model.Resource;
import com.yunpos.rewriter.binding.BindingService;
import com.yunpos.rewriter.filter.ColumnFilter;
import com.yunpos.rewriter.filter.Filter;
import com.yunpos.rewriter.filter.FilterGroup;
import com.yunpos.rewriter.filter.KeyFilter;
import com.yunpos.rewriter.value.Value;
import com.yunpos.service.ResourceService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;


/**
 * 拦截未登录的用户信息
 *
 * @author kingbox
 */
@Service
public class DataSecurityInterceptor extends HandlerInterceptorAdapter {

    private static final Log logger = LogFactory.getLog(DataSecurityInterceptor.class);


    @Autowired
    private ResourceService resourceService;

    @Autowired
    private BindingService bindingService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        logger.info("--------------------------DataSecurityInterceptor--------------------------------");
        Map<String, Object> bindingParams = bindingService.getBindingParams();
        request.setAttribute(Application.BINDING_PARAMS_KEY, bindingParams);

        String path = request.getServletPath();
        List<Resource> resourceList = resourceService.getAllResources();
        if (resourceList != null && resourceList.size() > 0) {
            AntPathMatcher matcher = new AntPathMatcher();
            Comparator<String> matcherComparator = matcher.getPatternComparator(path);
            Optional<Resource> matchedResource = resourceList.stream().filter(r -> matcher.match(r.getPath(), path))
                    .sorted((r1, r2) -> matcherComparator.compare(r1.getPath(), r2.getPath()))
                    .findFirst();
            if (matchedResource.isPresent()) {
                FilterGroup filter = new FilterGroup();
                filter.setChildren(matchedResource.get().getFilterGroupList().stream().map(g -> {
                    FilterGroup group = new FilterGroup();
                    group.setFilterList(g.getFilterList().stream().map(f -> {
                        Filter.Op op = Filter.Op.fromCode(f.getOp());
                        Value.DataType dataType = Value.DataType.fromCode(f.getDataType());
                        Value value = Value.fromSplitedStr(dataType, f.getFilterValue(), ",");
                        switch (FilterDifinition.Type.fromCode(f.getType())) {
                            case COLUMN_FILTER:
                                return new ColumnFilter(f.getColName(), op, value);
                            case KEY_PARAM_FILTER:
                                return new KeyFilter(op, value, f.getColName(), f.getKeyTable(), f.getKeyColumn(), f.getPrimaryColumn());
                            default:
                                throw new java.lang.IllegalArgumentException("unsupported filter type with code" + f.getType());
                        }
                    }).collect(Collectors.toList()));
                    return group;
                }).collect(Collectors.toList()));
                request.setAttribute("filter", filter);
            }
        }
        logger.info("--------------------------DataSecurityInterceptor--------------------------------");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }
}
