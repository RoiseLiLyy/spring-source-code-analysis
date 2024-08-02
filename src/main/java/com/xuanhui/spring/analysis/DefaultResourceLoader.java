package com.xuanhui.spring.analysis;

import org.springframework.core.io.*;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import java.net.MalformedURLException;
import java.net.URL;

public class DefaultResourceLoader extends org.springframework.core.io.DefaultResourceLoader implements ResourceLoader {


    private String placeholderPrefix = "${";
    private String placeholderSuffix = "}";
    private String simplePrefix = "";


    public DefaultResourceLoader(ClassLoader classLoader) {
    }

    @Override
    public ClassLoader getClassLoader() {
        return null;
    }

    /**
     * 根据 path 返回resource
     * <p>
     * path 的格式为 classpath      classpath:.*testdas.xml
     * 绝对路径file    E:\code\spring-source-code-analysis\src\main\resources\testdas.xml
     * 相对路径        /resource/testdas.xml
     *
     * @param location
     * @return
     */
    @Override
    public Resource getResource(String location) {

        // 如果现有的 protocolResolver , 直接解析
        // 如果解析不了 则判断各种resource
        for (ProtocolResolver protocolResolver : getProtocolResolvers()) {

            Resource resource = protocolResolver.resolve(location, this);
            if (resource != null) {
                return resource;
            }

        }

        if (location.startsWith("classpath")) {
            return new ClassPathContextResource(location, getClassLoader());

        } else if (location.startsWith("/")) {
            return new ClassPathResource(location);

        }


        try {
            boolean fileURL = ResourceUtils.isFileURL(new URL(location));
            FileUrlResource fileUrlResource = new FileUrlResource(new URL(location));
            UrlResource urlResource = new UrlResource(new URL(location));

            return fileURL ? fileUrlResource : urlResource;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 从一个字符串中获取最后一个相匹配的字符
     * <p>
     * 匹配字符 strA= “aavf”,  原字符串为strB = "dadfagdasgeagdasx"
     * <p>
     * 基本逻辑
     * 1.
     * 遍历字符串 strB  开始位置index = 0
     * 匹配字符长度 int m = strA.length   // 4
     * <p>
     * <p>
     * <p>
     * 1.1. 遍历字符串 strA， 如果 strB 中有符合的字符串 str[i] = strB[m+i] 则复合条件
     * 3. 存在1个字符串符合， 结果res++ , index = index + m
     * 4. 不符合， res = res; index++
     *
     * @param buf
     * @param startIndex
     * @return
     */
    private int findPlaceholderEndIndex(CharSequence buf, int startIndex) {
        int index = startIndex + this.placeholderPrefix.length();
        int withinNestedPlaceholder = 0;
        while (index < buf.length()) {
            if (StringUtils.substringMatch(buf, index, this.placeholderSuffix)) {
                if (withinNestedPlaceholder > 0) {
                    withinNestedPlaceholder--;
                    index = index + this.placeholderSuffix.length();
                } else {
                    return index;
                }
            } else if (StringUtils.substringMatch(buf, index, this.simplePrefix)) {
                withinNestedPlaceholder++;
                index = index + this.simplePrefix.length();
            } else {
                index++;
            }
        }
        return -1;
    }


}
