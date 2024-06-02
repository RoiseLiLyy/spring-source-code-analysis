package com.xuanhui.spring.until;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.expression.StandardBeanExpressionResolver;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class AbstractApplicationContextDemo {
    private  final AtomicBoolean active = new AtomicBoolean();

    private volatile List<String> beanDefinitionNames = new ArrayList<>(256);


    private final Map<String, Object> singletonFactories = new ConcurrentHashMap<>(256);
    /**
     * 一级缓存
     */
    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);


    /**
     * 二级缓存
     */
    private final Map<String, Object> earlySingletonObject = new ConcurrentHashMap<>(256);


    /**
     * 1.准备刷新
     *
     *
     */
    public void refresh() {
        // 1.准备刷新
        prepareRefresh();
        // 2. 获取刷新beanFactory
        ConfigurableListableBeanFactory beanFactory = obtainRefreshBeanFactory();

        // 3.初始化beanFactory
        prepareBeanFactory(beanFactory);

        // 暂时没啥用 扩展
        postProcessBeanFactory(beanFactory);

        // ?
        invokeBeanFactoryPostProcess(beanFactory);

        // 注册bean post processor
        registerBeanPostProcessor();

        // 初始化信息资源： 国际化
        initMessageSource();

        //注册多播器 （广播器）
        initApplicationEventMulticaster();

        onRefresh();
        // 注册监听器
        registerListener();

        //实例化所有非懒加载的对象
        finishBeanFactoryInitalization(beanFactory);
    }

    /**
     * 实例化所有非懒加载的对象
     *
     *  实例化对象
     * @param beanFactory
     */
    private void finishBeanFactoryInitalization(ConfigurableListableBeanFactory beanFactory) {
        // 对象转换
        beanFactory.setConversionService(new DefaultConversionService());

        //内置值处理器:  ${}
        beanFactory.addEmbeddedValueResolver(s->getEnvironment().resolvePlaceholders(s));
        //设置不需修改的bean, 例如 static, 单例模式？
        beanFactory.freezeConfiguration();

        preInstantiateSingletons();
    }

    private void preInstantiateSingletons() {
        List<String> beanDefinitionName = new ArrayList<>(this.beanDefinitionNames);
        for (String beanName: beanDefinitionName ) {
            RootBeanDefinition rd = getMergedLocalBeanDefinition(beanName);
            getBean(beanName);

        }

    }

    private void getBean(String beanName) {
        doGetBean(beanName);
    }

    /**
     * bean 的三级缓存： 为了解决循环依赖
     * @param beanName
     */
    private void doGetBean(String beanName) {
        Object shareInstance =  getSinglteon(beanName);
    }

    private Object getSinglteon(String beanName) {
        return null;
    }




    /**
     * 获取子类和父类的关联信息
     * @param beanName
     * @return
     */
    private RootBeanDefinition getMergedLocalBeanDefinition(String beanName) {
        return new RootBeanDefinition();
    }

    private void registerListener() {
    }

    private void onRefresh() {
    }

    private void initApplicationEventMulticaster() {
    }

    /**
     *
     */
    private void initMessageSource() {
    }

    /**
     * 注册bean post processor
     *
     * 1. 实例化并且注册所有的 beanProcessor的 bean
     */
    private void registerBeanPostProcessor() {

    }

    private void invokeBeanFactoryPostProcess(ConfigurableListableBeanFactory beanFactory) {
    }

    private void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
    }

    /**
     * 初始化beanFactory
     *
     * 1.设置 spel 表达式支持 #{jdbc.url}
     * @param beanFactory
     */
    private void prepareBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        beanFactory.setBeanExpressionResolver(new StandardBeanExpressionResolver());
    }


    /**
     * 准备刷新
     *
     * 1.利用原子性的AutionBoolean设置一些属性
     * 2. 初始化属性资源
     * 3. 获取环境信息
     * 4.准备监听器和事件的对象集合， 默认为new ArrayList
     */
    private void prepareRefresh() {

        //利用原子性的AutionBoolean设置一些属性

        this.active.set(true);

        // 初始化属性资源  实际上啥也没做 只是为了扩展
        initPropertySources();

        //获取基础环境信息
        getEnvironment().validateRequiredProperties();
    }


    public void invokeAwareMethod(String beanName, Object bean) {

    }


    public void applyBeanPostProcessorsInitiation() {

    }


    /**
     * 获取刷新beanFactory
     *
     *
     *   1.刷新beanFactory
     *   2.获取beanFactory
     *   创建容器对象DefaultListableBeanFactory
     *   3.加载所有bean到beanFactory中 , beanDefinitionNames , beanDefinitionMap(加载xml文件的属性值到当前工厂中)
     */
    private ConfigurableListableBeanFactory obtainRefreshBeanFactory() {
        refreshBeanFactory();
        return  getBeanFactory();
    }

    private ConfigurableListableBeanFactory getBeanFactory() {
        String beanName = "";
        getBean(beanName);
        return  null;
    }

    private void refreshBeanFactory() {
    }

    /**
     *
     * 通过继承父类， 子类在创建时，父类也会实现基础构造
     *    父类会定制化属性资源
     *
     * @return 返回一些基础的资源信息
     */
    private ConfigurableEnvironment getEnvironment() {
        return  null;
    }


    /**
     *
     *
     * 初始化属性资源  实际上啥也没做 只是为了扩展
     */
    private void initPropertySources() {
    }
}
