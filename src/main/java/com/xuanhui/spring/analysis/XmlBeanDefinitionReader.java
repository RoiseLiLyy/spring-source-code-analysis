package com.xuanhui.spring.analysis;

import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.xml.BeanDefinitionDocumentReader;
import org.springframework.beans.factory.xml.ResourceEntityResolver;
import org.springframework.beans.factory.xml.XmlReaderContext;
import org.springframework.core.NamedThreadLocal;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.lang.Nullable;
import org.w3c.dom.Document;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

/**
 * 解析xml 文件的 bean definition reader
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    // todo ?
    final ThreadLocal<Set<EncodedResource>> resourceCurrentlyBeingLoaded = new NamedThreadLocal<Set<EncodedResource>>("") {
        @Override
        protected Set<EncodedResource> initialValue() {
            return new HashSet<>();
        }
    };


    public XmlBeanDefinitionReader() {
    }

    public XmlBeanDefinitionReader(DefaultListableBeanFactory beanFactory) {
        super(beanFactory);
    }

    @Nullable
    private EntityResolver entityResolver;

    public void setEntityResolver(ResourceEntityResolver resourceEntityResolver) {
        this.entityResolver = resourceEntityResolver;
    }


    @Override
    public int loadBeanDefinitions(Resource resource) throws BeanDefinitionStoreException {
        return loadBeanDefinitions(new EncodedResource(resource));
    }

    @Override
    public int loadBeanDefinitions(String location) throws BeanDefinitionStoreException {
        return 0;
    }

    public int loadBeanDefinitions(EncodedResource encodedResource) {
        Set<EncodedResource> currentResources = this.resourceCurrentlyBeingLoaded.get();

        try (
                // 获取Resource 对象中的xml 文件流对象
                InputStream inputStream = encodedResource.getResource().getInputStream();
        ) {

            // 将inputStream 转换为inputSource
            // inputSource 负责xml 文件解析
            InputSource inputSource = new InputSource(inputStream);

            if (encodedResource.getEncoding() != null) {
                inputSource.setEncoding(encodedResource.getEncoding());
            }

            return doLoadBeanDefinitions(inputSource, encodedResource.getResource());


        } catch (IOException e) {
            throw new BeanDefinitionStoreException("");
        } finally {
            currentResources.remove(encodedResource);
            if (currentResources.isEmpty()) {
                this.resourceCurrentlyBeingLoaded.remove();
            }
        }
    }

    /**
     * inputSource 解析xml文件
     *
     * @param inputSource
     * @param resource
     * @return
     */
    public int doLoadBeanDefinitions(InputSource inputSource, Resource resource) {
        Document doc = doLoadDocument(inputSource, resource);

        int count = registerBeanDefinitions(doc, resource);
        return count;
    }

    public int registerBeanDefinitions(Document doc, Resource resource) {
        BeanDefinitionDocumentReader documentReader = createBeanDefinitionDocument();
        int countBefore = getRegistry().getBeanDefinitionCount();
        documentReader.registerBeanDefinitions(doc, createReaderContext(resource));
        return getRegistry().getBeanDefinitionCount() - countBefore;
    }

    public XmlReaderContext createReaderContext(Resource resource) {
        return null;
    }

    public Document doLoadDocument(InputSource inputSource, Resource resource) {
        return null;
    }

    public BeanDefinitionDocumentReader createBeanDefinitionDocument() {
        return null;
    }


}
