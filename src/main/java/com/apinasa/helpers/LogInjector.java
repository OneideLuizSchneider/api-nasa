package com.apinasa.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import java.lang.reflect.Field;

@Component
public class LogInjector implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessBeforeInitialization(final Object bean, String name) throws BeansException {
        ReflectionUtils.doWithFields(bean.getClass(), (Field field) -> {
            ReflectionUtils.makeAccessible(field);
            if (field.getAnnotation(Loggable.class) != null) {
                Logger log = LoggerFactory.getLogger(bean.getClass());
                field.set(bean, log);
            }
        });
        return bean;
    }
}
