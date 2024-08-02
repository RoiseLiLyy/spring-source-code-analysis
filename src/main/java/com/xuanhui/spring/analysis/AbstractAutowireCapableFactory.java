package com.xuanhui.spring.analysis;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;

import java.util.HashSet;
import java.util.Set;

public class AbstractAutowireCapableFactory implements BeanFactory {

    // 定义在依赖检查和自动绑定时要忽略的依赖类型，例如String, 默认为null
    private Set<Class<?>> ignoreDependencyTypes = new HashSet<>();


    // 定义在依赖检查和自动绑定时要忽略的依赖接口 ，默认情况是只有beanFactory被忽略
    /**
     * 主要是忽略给定接口的自动装配功能，
     * 比如class A 包含class B, 正常情况下，初始化 A 的同时也会初始化 B， 但是如果B  继承了 BeanNameAware 接口， B不会自动注入
     * 典型应用是  通过其他方式解析Application上下文注册依赖      类似于BeanFactory通过BeaFactoryAware进行注入 或者是ApplicationContext 通过ApplicationContextAware进行注入， 其他见ServletContextAware
     */

    private Set<Class<?>> ignoreDependencyInterfaces = new HashSet<>();

    public AbstractAutowireCapableFactory() {
        super();

        this.ignoreDependencyInterfaces.add(BeanNameAware.class);
        this.ignoreDependencyInterfaces.add(BeanFactoryAware.class);
        this.ignoreDependencyInterfaces.add(BeanClassLoaderAware.class);

    }
}
