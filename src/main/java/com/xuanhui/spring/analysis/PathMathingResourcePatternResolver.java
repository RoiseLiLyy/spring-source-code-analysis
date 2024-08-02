package com.xuanhui.spring.analysis;

import org.springframework.core.io.Resource;

import java.net.MalformedURLException;

/**
 * 此类支持ant模式 eg  * * /.xml
 */
public class PathMathingResourcePatternResolver implements ResourcePatterResolver {
    private ResourceLoader resourceLoader;

    @Override
    public ClassLoader getClassLoader() {
        return null;
    }

    @Override
    public Resource getResource(String location) throws MalformedURLException {
        return this.getResourceLoader().getResource(location);
    }

    @Override
    public Resource[] getResources(String locationPattern) {
        return new Resource[0];
    }


    /**
     * 构造方法
     */
    public PathMathingResourcePatternResolver() {
    }

    /**
     * 构造方法
     */
    public PathMathingResourcePatternResolver(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    /**
     * 构造方法
     */
    public PathMathingResourcePatternResolver(ClassLoader classLoader) {
        this.resourceLoader = new DefaultResourceLoader(classLoader);
    }

    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
