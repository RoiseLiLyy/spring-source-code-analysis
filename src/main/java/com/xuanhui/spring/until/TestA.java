package com.xuanhui.spring.until;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class TestA {

    class ClassPathXmlApplicationContext extends AbstractRefreshableApplicationContext {
        public ClassPathXmlApplicationContext(ApplicationContext parent, String[] localcations) {
            super(parent);
            setConfigLocations(localcations);
            refresh();
        }

        private void setConfigLocations(String[] localcations) {
        }


    }


    abstract class AbstractApplicationContext {

        public AtomicBoolean active = new AtomicBoolean();


        public AtomicBoolean closed = new AtomicBoolean();

        public Set<ApplicationListener> earlyApplicationListener;
        public Set<ApplicationListener> applicationListener;

        public AbstractApplicationContext(ApplicationContext parent) {
            this();
        }

        public AbstractApplicationContext() {

        }

        public void refresh() {
            // 获取环境变量 初始化 applicationListener， 设置开始时间， actice , close 状态
            prepareRefresh();

            // 获取ListableBeanFactory
            BeanFactory beanFactory = obtainFreshBeanFactory();
            prepareBeanFactory(beanFactory);
            postProcessBeanFactory(beanFactory);
            invokeBeanFactoryPostProcessors(beanFactory);
            registerBeanPostProcessors(beanFactory);
            initMessageSource();
            initApplicationEventMulticaster();
            onfresh();
            registerListeners();
            finishBeanFactoryInitation(beanFactory);
            finishRefresh();
        }

        private void registerListeners() {

        }


        private void invokeBeanFactoryPostProcessors(BeanFactory beanFactory) {
        }

        private void postProcessBeanFactory(BeanFactory beanFactory) {
        }

        private void finishRefresh() {
        }

        private void finishBeanFactoryInitation(BeanFactory beanFactory) {
        }

        private void onfresh() {
        }

        private void initApplicationEventMulticaster() {
        }

        private void initMessageSource() {
        }

        private void registerBeanPostProcessors(BeanFactory beanFactory) {
        }

        private void registerPostProcessorBeanFactory(BeanFactory beanFactory) {
        }

        private void beanFactoryPostProcessor(BeanFactory beanFactory) {
        }

        private void prepareBeanFactory(BeanFactory beanFactory) {
        }

        private ConfigurableListableBeanFactory obtainFreshBeanFactory() {
            refreshBeanFactory();
            return getBeanFactory();
        }

        abstract void refreshBeanFactory();

        abstract ConfigurableListableBeanFactory getBeanFactory();

        private void prepareRefresh() {

            long time = System.currentTimeMillis();
            this.active.set(true);
            this.closed.set(false);
            initPropertiesSource();
            getEnvironment().validateRequiredProperties();
            earlyApplicationListener = new HashSet<>();
            applicationListener.addAll(earlyApplicationListener);
        }

        private void initPropertiesSource() {
        }

        private ConfigurableEnvironment getEnvironment() {
            return null;
        }

    }


    class AbstractRefreshableApplicationContext extends AbstractApplicationContext {

        public  boolean hasBeanFactory = false;
        DefaultListableBeanFactory beanFactory;
        public AbstractRefreshableApplicationContext(ApplicationContext parent) {
            super(parent);
        }

        @Override
        void refreshBeanFactory() {
            if(hasBeanFactory) {
                destroyBeanFactory();
                closeBeanFactory();
            }
            DefaultListableBeanFactory beanFactory = createBeanFactory();
            beanFactory.setSerializationId("");
            customizeBeanFactory(beanFactory);
            loadBeanDefinitions(beanFactory);

            this.beanFactory = beanFactory;

        }

        private  void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        }

        private void customizeBeanFactory(DefaultListableBeanFactory beanFactory) {

        }

        private DefaultListableBeanFactory createBeanFactory() {
            return null;
        }

        private void closeBeanFactory() {
        }

        private void destroyBeanFactory() {
        }

        @Override
        ConfigurableListableBeanFactory getBeanFactory() {
            return null;
        }
    }

}
