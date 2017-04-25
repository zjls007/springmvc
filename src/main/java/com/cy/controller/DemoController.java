package com.cy.controller;

import com.cy.common.annotation.FormModel;
import com.cy.common.converter.DateConverter;
import com.cy.common.converter.EnumConverter;
import com.cy.common.enumeration.Color;
import com.cy.entity.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Date;
import java.util.List;

/**
 * Created by zxj on 2017/4/7.
 *   @see RequestMappingHandlerMapping
 *   @see RequestMappingHandlerAdapter
 */
@RestController
public class DemoController {

    /**
     * springMvc 默认不支持直接传java.util.Date类型数据
     * 例：http://localhost:8080/dateConverter/2017-4-22
     *    页面会包400错误
     *    org.springframework.web.method.annotation.MethodArgumentTypeMismatchException: Failed to convert value of type 'java.lang.String' to required type 'java.util.Date'
     * 这里扩展支持传输Date类型：
     * 参考：{@link DateConverter}
     * <beans ...>
     * <mvc:annotation-driven conversion-service="conversionService">
     * </mvc:annotation-driven>
     * <bean id="conversionService" class="org.springframework.format.support.FormattingConversionServiceFactoryBean">
     *     <property name="converters">
     *         <set>
     *             <bean class="com.cy.common.converter.DateConverter" />
     *         </set>
     *     </property>
     * </bean>
     * </beans>
     * 第二种方案不加配置的情况下，参考： {@link Bean} 使用 {@link DateTimeFormat}
     * @param date
     * @return
     */
    @RequestMapping("dateConverter/{date}")
    @ResponseBody
    public Object dateConverter(@PathVariable("date") Date date) {
        return date;
    }

    /**
     * 默认不支持直接传枚举类型
     * 参考: {@link DemoController#dateConverter(Date)}
     * 在set中加入 {@link EnumConverter}
     * http://localhost:8080/enumConverter/RED
     * @param color
     * @return
     */
    @RequestMapping("enumConverter/{color}")
    @ResponseBody
    public Object enumConverter(@PathVariable("color") Color color) {
        return color;
    }

    /**
     * SpringMvc 默认不支持直接传List对象, 但支持接收对象中的List，参考: {@link DemoController#beanHasList(BeanHasList)}
     * @RequestBody 内部采用MappingJackson2HttpMessageConverter把json字符串转成json数组，再把json数组封装成List对象
     * http-headers必须包含： Content-Type：application/json
     * @param list
     * @return
     */
    @RequestMapping("listItem")
    @ResponseBody
    public Object listItem(@RequestBody List<ListItem> list) {
        return list;
    }

    /**
     * http://localhost:8080/beanHasList?list[0].a=1&list[0].b=1
     * 其他接收List可参考: {@link DemoController#listItem(List)}
     * @param bean
     * @return
     */
    @RequestMapping("beanHasList")
    @ResponseBody
    public Object beanHasList(BeanHasList bean) {
        return bean;
    }

    @GetMapping("strParam")
    @ResponseBody
    public Object strParam(String param1, String param2) {
        return String.format("param1:[%s],param2:[%s]", param1, param2);
    }

    @GetMapping("beanParam")
    @ResponseBody
    public Object beanParam(ListItem listItem) {
        return listItem;
    }

    @RequestMapping("test2")
    public Object g(@FormModel("b1") Bean1 b1, @FormModel("b2") Bean2 b2) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(b1) + objectMapper.writeValueAsString(b2);
    }

}
