package com.xuanhui.spring.analysis;

import org.springframework.beans.factory.config.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultListableBeanFactory extends org.springframework.beans.factory.support.DefaultListableBeanFactory {

    private String serializationId;


    /**
     * 在 createBeanFactory 时 初始化 beanDefinitionMap 作为 存储 bean definition 的容器
     */
    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    public String getSerializationId() {
        return serializationId;
    }

    public void setSerializationId(String serializationId) {
        this.serializationId = serializationId;
    }
}
