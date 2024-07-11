package com.xuanhui.spring.analysis;

import org.springframework.beans.factory.xml.ResourceEntityResolver;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;


public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {

    /**
     * 解析xml
     *
     * @param beanFactory
     */
    @Override
    void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {

        // 加载xml reader, 委托模式
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        xmlBeanDefinitionReader.setEnvironment(this.getEnvironment());

        xmlBeanDefinitionReader.setEntityResolver(new ResourceEntityResolver(this));

        // this 是因为 AbstractXmlApplicationContext 实现了 ResourceLoader，作为子类实现父类的构造
        xmlBeanDefinitionReader.setResourceLoader(this);

        initBeanDefinition(xmlBeanDefinitionReader);


        // todo  此方法 与上边方法名字一致， 传参不一样
        loadBeanDefinitions(xmlBeanDefinitionReader);
    }

    private void initBeanDefinition(AbstractBeanDefinitionReader xmlBeanDefinitionReader) {
    }

    private Environment getEnvironment() {

        return null;
    }

    /**
     * 委托给xml bean definition reader 进行解析
     *
     * @param xmlBeanDefinitionReader
     */
    protected void loadBeanDefinitions(AbstractBeanDefinitionReader xmlBeanDefinitionReader) {
        Resource[] configResources = getConfigResources();
        if (configResources != null) {
            xmlBeanDefinitionReader.loadBeanDefinitions(configResources);
        }
        Resource[] configLocations = getLocalConfigResources();
        if (configLocations != null) {
            xmlBeanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    private Resource[] getLocalConfigResources() {
        return null;
    }

    private Resource[] getConfigResources() {
        return null;
    }
}
