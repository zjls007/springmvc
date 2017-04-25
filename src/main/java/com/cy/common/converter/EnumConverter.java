package com.cy.common.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import static javafx.scene.input.KeyCode.R;

/**
 * Created by zxj on 2017/4/7.
 */
public class EnumConverter implements ConverterFactory<String, Enum> {

    public <T extends Enum> Converter<String, T> getConverter(Class<T> clazz) {
        return new StringToEnum(clazz);
    }

    private class StringToEnum<T extends Enum> implements Converter<String, T> {

        private final Class<T> enumType;

        public StringToEnum(Class<T> enumType) {
            this.enumType = enumType;
        }

        public T convert(String source) {
            if (source.length() == 0) {
                return null;
            }
            return (T) Enum.valueOf(this.enumType, source.trim());
        }
    }


}
