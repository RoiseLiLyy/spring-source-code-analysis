package com.xuanhui.spring.analysis;

import org.springframework.beans.factory.ObjectFactory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 主要方法 getSingleton
 */
public class DefaultSingletonBeanRegistry extends AbstractBeanFactory {

    //  一级缓存： 保存 beanName 和 创建bean实例 之间的关系
    // 只有完整的bean 才放入一级缓存
    private final Map<String, Object> singletonObjects = new HashMap<>();

    /**
     * 二级缓存
     * 1.保存 beanName 和 创建bean实例 之间的关系
     * 2. 与singletonObjects 不同的是：
     * 2.1 当一个 单例bean 被存放于 earlySingletonObjects, 当bean 还在创建时， 可以通过getBean 方法获取到，目的是为了检测循环依赖
     * <p>
     * <p>
     * 3. 所有对象创建完毕后， size 为0
     * 4.用于存储在创建bean 早期对创建原始bean 的一个引用， 所谓原始bean ,即使用beanFactory 或者构造方法创建的对象
     */
    private final Map<String, Object> earlySingletonObjects = new HashMap<>();


    // 保存 beanName 和 创建 beanFactory 之间的关系
    // 存储在 spring 内部所使用的beanName 和 beanFactory的引用关系，一旦最终对象被创建（通过objectFactory.getObject()）, 此关系将被remove
    // 所有对象创建完毕后， size 为0
    private final Map<String, ObjectFactory<?>> singletonFactories = new HashMap<>();


    private final Set<String> singletonCurrentlyInCreation = new HashSet<>();

    // 用来保存所有已经注册的bean
    private final Set<String> registeredSingletons = new HashSet<>();


    /**
     * 获取单例bean
     *
     * @param beanName
     * @param allowEarlyReference
     * @return
     */
    public Object getSingleton(String beanName, boolean allowEarlyReference) {

        Object singletonObject = this.singletonObjects.get(beanName);
        if (singletonObject == null && this.isSingletonCurrentlyInCreation(beanName)) {

            // 对singletonObjects加锁
            synchronized (singletonObjects) {

                singletonObject = this.earlySingletonObjects.get(beanName);

                if (singletonObject == null && allowEarlyReference) {

                    ObjectFactory<?> singletonFactory = (ObjectFactory<?>) this.singletonFactories.get(beanName);

                    if (singletonFactory != null) {
                        singletonObject = singletonFactory.getObject();
                        // 放入二级缓存
                        this.earlySingletonObjects.put(beanName, singletonObject);
                        // 删除三级缓存
                        this.singletonFactories.remove(beanName);
                    }


                }
            }
        }


        return singletonObject;
    }

    private boolean isSingletonCurrentlyInCreation(String beanName) {

        return this.singletonCurrentlyInCreation.contains(beanName);
    }


    public void addSingletonFactory(String beanName, ObjectFactory ob) {
        synchronized (this.singletonObjects) {
            if (!this.singletonObjects.containsKey(beanName)) {
                this.singletonFactories.put(beanName, ob);
                this.earlySingletonObjects.remove(beanName);
                this.registeredSingletons.add(beanName);
            }
        }
    }


    @Override
    protected boolean containsBeanDefinition(String beanName) {
        return false;
    }
}


