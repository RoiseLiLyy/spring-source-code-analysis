package com.xuanhui.spring.analysis;

public interface BeanFactory {

    /**
     *  通过bean 的名字 获取 bean
     * @param name
     * @return
     * @param <T>
     */
    // <T> T getBean(String name);


    /**
     *  通过bean name 获取是否存在该 bean
     * @param name
     * @return
     */
    //  boolean containerBean(String name);


    /**
     *  是否是单例模式
     * @param name
     * @return
     */
    // boolean isSingleton(String name);

    /**
     * 是否是原型模式
     * @param name
     * @return
     */
    // boolean isProperType(String name);

    /**
     * 获取别名
     * @param name
     * @return
     */
    // String getAlias(String name);
}
