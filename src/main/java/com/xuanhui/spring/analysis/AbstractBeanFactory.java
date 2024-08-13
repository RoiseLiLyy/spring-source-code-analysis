package com.xuanhui.spring.analysis;

import org.springframework.beans.factory.BeanCurrentlyInCreationException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * 针对bean的处理
 * <p>
 * 1.doGetBean
 */
public abstract class AbstractBeanFactory implements BeanFactory {


    /**
     * refresh  ->   fininshBeanFactoryInitation  ->   doGetBean
     * <p>
     * doGetBean
     * <p>
     * 1. 转换beanName
     * 2. 从缓存中加载单例
     * 3.实例化bean
     * 4.检查原型模式的依赖
     * 5.检测parentBeanFactory
     * 6. 通过beanName 获取RootBeanDefinition
     * 7.寻找依赖
     * 8.针对不同的scope 进行处理
     * 9. 类型转换
     *
     * @param name
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> T doGetBean(String name) throws Exception {
        String beanName = this.transformedName(name);

        Object sharedInstance = this.getSingltion(beanName);
        Object bean;

        if (sharedInstance != null) {
            bean = this.getObjectForInstanceBean(sharedInstance, name, beanName, null);
            throw new Exception("");
        } else {


            if (this.isPrototypeCurrentlyInCreation(beanName)) {
                throw new BeanCurrentlyInCreationException(beanName);
            }

            BeanFactory parenBeanFactory = this.getParentBeanFactory();

            if (parenBeanFactory != null) {

            }

        }


        return null;
    }

    private boolean isPrototypeCurrentlyInCreation(String beanName) {
        return false;
    }

    private BeanFactory getParentBeanFactory() {
        return null;
    }

    /**
     * @param beanName
     * @return
     */
    private Object getObjectForInstanceBean(Object beanInstance, String name, String beanName, RootBeanDefinition mbd) throws Exception {

        if (BeanFactoryUtils.isFactoryDereference(name)) {
            if (beanInstance == null) {
                return beanInstance;
            } else if (!(beanInstance instanceof FactoryBean)) {
                throw new Exception("");
            } else {
                if (mbd != null) {
                    // mbd.isFactoryBean = true;
                }

                return beanInstance;
            }
        } else if (!(beanInstance instanceof FactoryBean)) {
            return beanInstance;
        } else {
            Object ob = null;

            if (mbd != null) {
                // mbd.isFactoryBean = true;  
            } else {
                ob = this.getCachedObjectForFactoryBean(beanName);
            }

            if (ob == null) {
                FactoryBean factory = (FactoryBean) beanInstance;

                if (mbd == null && this.containsBeanDefinition(beanName)) {
                    mbd = this.getMergedLocalBeanDefinition(beanName);

                }

                boolean synthetic = mbd != null && mbd.isSynthetic();
                ob = this.getObjectFromFactoryBean(factory, beanName, !synthetic);
            }
        }

        return null;
    }

    private Object getObjectFromFactoryBean(FactoryBean factory, String beanName, boolean b) {
        return null;
    }

    private RootBeanDefinition getMergedLocalBeanDefinition(String beanName) {
        return null;
    }

    protected abstract boolean containsBeanDefinition(String beanName);

    private Object getCachedObjectForFactoryBean(String beanName) {
        return null;
    }

    private Object getSingltion(String beanName) {
        return null;
    }

    private String transformedName(String name) {
        return null;
    }
}
