package com.xuanhui.spring.analysis;


import org.springframework.core.io.Resource;

import java.net.MalformedURLException;

/**
 * Resource 是定义资源
 * <p>
 * ResourceLoader 是加载资源
 * <p>
 * 基础实现类DefaultResourceLoader
 */
public interface ResourceLoader {

    ClassLoader getClassLoader();

    Resource getResource(String location) throws MalformedURLException;

}
