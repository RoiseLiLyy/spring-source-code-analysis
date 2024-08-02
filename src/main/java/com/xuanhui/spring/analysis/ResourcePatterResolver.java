package com.xuanhui.spring.analysis;

import org.springframework.core.io.Resource;

public interface ResourcePatterResolver extends ResourceLoader {

    /**
     * 相当于 foreach ResourceLoader.getResource();
     * <p>
     * 支持classpath*
     *
     * @param location
     * @return
     */
    Resource[] getResources(String location);
}
