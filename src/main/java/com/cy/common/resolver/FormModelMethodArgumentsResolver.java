package com.cy.common.resolver;

import com.cy.common.annotation.FormModel;
import org.springframework.beans.BeanUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.lang.reflect.Field;
import java.util.Iterator;

/**
 * Created by zxj on 2017/4/12.
 */
public class FormModelMethodArgumentsResolver implements HandlerMethodArgumentResolver {

    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(FormModel.class);
    }
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        String objName = parameter.getParameterName() + ".";
        Object o = BeanUtils.instantiate(parameter.getParameterType());
        StringBuffer tmp;
        String[] val;
        Field[] frr = parameter.getParameterType().getDeclaredFields();
        for (Iterator<String> itr = webRequest.getParameterNames(); itr
                .hasNext();) {
            tmp = new StringBuffer(itr.next());
            if (tmp.indexOf(objName) < 0)continue;
            for (int i = 0; i < frr.length; i++) {
                frr[i].setAccessible(true);
                if (tmp.toString().equals(objName + frr[i].getName())) {
                    val = webRequest.getParameterValues(tmp.toString());
                    frr[i].set(o, val[0]);
                }
            }
        }
        return o;
    }
}