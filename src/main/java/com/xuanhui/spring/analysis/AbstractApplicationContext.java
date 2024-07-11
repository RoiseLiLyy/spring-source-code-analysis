package com.xuanhui.spring.analysis;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;


/**
 * AbstractApplicationContext  extends ListableBeanFactory
 * ListableBeanFactory  extends BeanFactory
 *
 *
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ResourcePatternResolver {
    private ResourcePatternResolver resourcePatternResolver;


    public void refresh() {
        prepareRefresh();
        obtainBeanFactory();
        prepareBeanFactory();
        postProcessorBeanFactory();
        invokeBeanFactoryPostProcessor();
        registerBeanPostProcessor();
        initMessageSources();
        initApplicationMultiCaster();
        onRefresh();
        registerListeners();
        finishBeanFactoryInitation();
        finishRefresh();

    }

    private void initMessageSources() {
    }

    private void onRefresh() {
    }

    private void registerListeners() {
    }

    private void finishRefresh() {
    }

    private void finishBeanFactoryInitation() {
    }

    private void initApplicationMultiCaster() {
    }

    private void registerBeanPostProcessor() {
    }

    private void invokeBeanFactoryPostProcessor() {
    }

    private void postProcessorBeanFactory() {
    }

    private void prepareBeanFactory() {
    }

    /**
     * 涉及模板方法
     * <p>
     * 子类实现接口
     *
     * @return
     */
    private BeanFactory obtainBeanFactory() {
        refreshBeanFactory();
        BeanFactory beanFactory = getBeanFactory();
        return beanFactory;
    }

    private void prepareRefresh() {


    }


    private BeanFactory getBeanFactory() {
        return null;
    }

    /**
     * 创建bean工厂
     */
    protected abstract void refreshBeanFactory();


    @Override
    public Resource[] getResources(String locationPattern) throws IOException {
        return this.resourcePatternResolver.getResources(locationPattern);
    }

}
