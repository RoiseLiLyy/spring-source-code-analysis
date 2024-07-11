package com.xuanhui.spring.analysis;

import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.Assert;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;

public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {
    private Environment environment;

    @Nullable
    private ResourceLoader resourceLoader;

    @Override
    public int loadBeanDefinitions(String... locations) throws BeanDefinitionStoreException {
        Assert.notNull(locations, "locations is not null");
        int count = 0;
        for (int i = 0; i < locations.length; i++) {
            String location = locations[i];
            count += loadBeanDefinitions(location, null);

        }
        return count;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public void setResourceLoader(@Nullable ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public AbstractBeanDefinitionReader() {
    }


    public AbstractBeanDefinitionReader(DefaultListableBeanFactory beanFactory) {
        super();

    }


    @Override
    public BeanDefinitionRegistry getRegistry() {
        return null;
    }


    @Override
    public ClassLoader getBeanClassLoader() {
        return null;
    }

    @Override
    public BeanNameGenerator getBeanNameGenerator() {
        return null;
    }

    @Override
    public int loadBeanDefinitions(Resource... resources) throws BeanDefinitionStoreException {

        Assert.notNull(resources, "locations is not null");
        int count = 0;
        for (int i = 0; i < resources.length; i++) {
            Resource resource = resources[i];
            count += loadBeanDefinitions(resource);

        }
        return count;

    }


    /**
     * 从 配置的xml 文件地址中读取相应的resource
     *
     * @param location
     * @param actualResources
     * @return
     * @throws BeanDefinitionStoreException
     */
    public int loadBeanDefinitions(String location, @Nullable Set<Resource> actualResources) throws BeanDefinitionStoreException {

        ResourceLoader resourceLoader = getResourceLoader();
        if (resourceLoader == null) {
            throw new BeanDefinitionStoreException("");
        }

        if (resourceLoader instanceof ResourcePatternResolver) {
            try {
                Resource[] resources = ((ResourcePatternResolver) resourceLoader).getResources(location);
                int count = loadBeanDefinitions(resources);

                if (actualResources == null) {
                    Collections.addAll(actualResources, resources);
                }
                return count;


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            Resource resource = resourceLoader.getResource(location);
            int count = loadBeanDefinitions(resource);

            if (actualResources != null) {
                actualResources.add(resource);
            }
            return count;

        }
    }

    @Override
    public int loadBeanDefinitions(Resource resource) throws BeanDefinitionStoreException {
        return 0;
    }

    @Override
    public int loadBeanDefinitions(String location) throws BeanDefinitionStoreException {
        return 0;
    }

    @Override
    @Nullable
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
