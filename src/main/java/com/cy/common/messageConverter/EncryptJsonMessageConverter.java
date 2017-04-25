package com.cy.common.messageConverter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Created by zxj on 2017/4/11.
 */
public class EncryptJsonMessageConverter extends MappingJackson2HttpMessageConverter {

    @Override
    protected void writeInternal(Object object, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(object);
//        json = new BASE64Encoder().encode(json.getBytes());
        json = String.format("<![加密了:[%s]]>", json);
        super.writeInternal(json, type, outputMessage);
    }

}
