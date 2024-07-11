package com.xuanhui.spring.analysis;

import javax.annotation.Nullable;


/**
 * 实现了最基础的父类 {@link org.springframework.context.ApplicationContext}
 * 每次实例化会创建一个内部的bean factory
 * <p>
 * 提供一个 abstract方法 loadBeanDefinitions 给子类实现
 * <p>
 * 注意下子类
 * <p>
 * 关于beanFactory 的刷新
 * 存在锁 beanFactoryMonitor,  刷新 beanFactory， 取消刷新beanFactory， 关闭beanFactory， 判断beanFactory 是否存在均会使用该锁
 * <p>
 * 主要实现方法是 refreshBeanFactory
 * <p>
 * <p>
 * createBeanFactory() 返回的是DefaultListableBeanFactory
 *
 * 关联到 DefaultListableBeanFactory
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {

    private final Object beanFactoryMonitor = new Object();

    @Nullable
    private DefaultListableBeanFactory beanFactory;

    @Override
    protected void refreshBeanFactory() {
        // 清理beanFactory
        if (hasBeanFactory()) {
            destroyBeanFactory();
            closeBeanFactory();
        }

        // 创建beanFactory
        DefaultListableBeanFactory beanFactory = createBeanFactory();

        //设置beanFactory唯一id
        beanFactory.setSerializationId(getId());

        // 设置beanFactory 的属性
        customizeBeanFactory(beanFactory);

        // 解析xml ,把xml的属性设置为bean definitional
        loadBeanDefinitions(beanFactory);

        synchronized (this.beanFactoryMonitor) {
            this.beanFactory = beanFactory;
        }
    }


    abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

    private void customizeBeanFactory(DefaultListableBeanFactory beanFactory) {
    }

    private String getId() {
        return "";

    }

    /**
     * 创建beanFactory
     *
     * @return
     */
    private DefaultListableBeanFactory createBeanFactory() {
        return null;
    }

    private void closeBeanFactory() {
    }

    private void destroyBeanFactory() {

    }

    private boolean hasBeanFactory() {
        return false;
    }
}
