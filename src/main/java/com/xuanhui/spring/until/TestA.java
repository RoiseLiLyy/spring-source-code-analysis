package com.xuanhui.spring.until;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;

import javax.annotation.Nullable;
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

        public void setConfigLocations(String[] localcations) {
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
            // todo
            // beanFactory.preInstantiateSingletons();
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

        // do nothing
        private void initPropertiesSource() {
            // todo
        }

        private ConfigurableEnvironment getEnvironment() {
            return null;
        }

    }

    class DefaultListableBeanFactory extends AbstractBeanFactory {

        public void preInstantiateSingletons() {
            String beanName = "";
            doGetBean(beanName);
        }

        @Override
        public void doCreateBean(String beanName) {

        }
    }

    abstract class AbstractBeanFactory {
        void doGetBean(String beanName) {
            doCreateBean(beanName);
        }

        public abstract void doCreateBean(String beanName);


        public void setSerializationId(String s) {
        }
    }

    abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

        @Override
        public void doCreateBean(String beanName) {

        }
    }

    class AbstractRefreshableApplicationContext extends AbstractApplicationContext {

        public boolean hasBeanFactory = false;

        @Nullable
        private String[] configLocations;
        DefaultListableBeanFactory beanFactory;

        public AbstractRefreshableApplicationContext(ApplicationContext parent) {
            super(parent);
        }

        public void setConfigLocations(String... locations) {
            this.configLocations = new String[locations.length];
            for (int i = 0; i < configLocations.length; i++) {
                this.configLocations[i] = resolvePath(locations[i]);
            }
        }

        private String resolvePath(String location) {
            return null;
        }

        @Override
        void refreshBeanFactory() {
            if (hasBeanFactory) {
                destroyBeanFactory();
                closeBeanFactory();
            }
            DefaultListableBeanFactory beanFactory = createBeanFactory();
            beanFactory.setSerializationId("");
            customizeBeanFactory(beanFactory);
            loadBeanDefinitions(beanFactory);

            this.beanFactory = beanFactory;

        }

        private void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
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
