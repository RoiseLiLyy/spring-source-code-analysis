package com.xuanhui.spring.demo;


import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;

public class ResourceLoaderDemo {


    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        PathMatchingResourcePatternResolver resourceLoader = new PathMatchingResourcePatternResolver();
        String location = "";

        location = "classpath*:*.xml";
        //location = "classpath*:/aa/**/*.xml";
        Resource[] resource = resourceLoader.getResources(location);
        System.err.println(resource[0].exists());




       /* System.err.println(resource.getClass());
        System.err.println(resource.exists());
        System.err.println(resource.lastModified());

        location = "classpath:testdas.xml";
        resource = resourceLoader.getResource(location);
        System.err.println(resource.getClass());
        System.err.println(resource.exists());
        System.err.println(resource.isReadable());
        System.err.println(resource.lastModified());



        location = "file:E:\\code\\spring-source-code-analysis\\src\\main\\resources\\testdas.xml";
        resource = resourceLoader.getResource(location);
        System.err.println(resource.getClass());
        System.err.println(resource.exists());
        System.err.println(resource.isReadable());
        System.err.println(resource.lastModified());*/

    }
}
