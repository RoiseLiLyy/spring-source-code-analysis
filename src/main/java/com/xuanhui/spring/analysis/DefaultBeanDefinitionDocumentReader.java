package com.xuanhui.spring.analysis;

import org.aspectj.weaver.ReferenceType;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;
import org.springframework.beans.factory.xml.XmlReaderContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.annotation.Nullable;

public class DefaultBeanDefinitionDocumentReader extends AbstractBeanDefinitionReader{
    @Nullable
    private BeanDefinitionParserDelegate delegate;
    public static final String PROFILE_ATTRIBUTE = "profile";


    protected void doRegisterBeanDefinitions(Element root) {

        BeanDefinitionParserDelegate parent = this.delegate;
        delegate = createDelegate(getReaderContext(), root, parent);

        if(this.delegate.isDefaultNamespace(root) ) {
            String profileSpec = root.getAttribute(PROFILE_ATTRIBUTE);

            // 检查给定的字符串是否包含实际文本。
            //更具体地说，如果String不为空，其长度大于0，并且至少包含一个非空白字符，则此方法返回true
            if(StringUtils.hasText(profileSpec)) {

                // 将字符转 按照指定的字符转换为 字符串数组....  将profileSpec 按照 ,;  切割为数组
                String[] specifiedProfiles =   StringUtils.tokenizeToStringArray(profileSpec, BeanDefinitionParserDelegate.MULTI_VALUE_ATTRIBUTE_DELIMITERS);


                if(getReaderContext().getEnvironment().acceptsProfiles(specifiedProfiles)) {
                    return;
                }
            }

        }

        preProcessXml(root);
        parseBeanDefinitions(root, this.delegate);
        postProcessXml(root);

        this.delegate = parent;


    }

    private void postProcessXml(Element root) {
    }

    private void parseBeanDefinitions(Element root, BeanDefinitionParserDelegate delegate) {
        if (delegate.isDefaultNamespace(root)) {
            NodeList nl = root.getChildNodes();
            for (int i = 0; i < nl.getLength(); i++) {
                Node node = nl.item(i);
                if (node instanceof Element) {
                    Element ele = (Element) node;
                    if (delegate.isDefaultNamespace(ele)) {
                        parseDefaultElement(ele, delegate);
                    }
                    else {
                        delegate.parseCustomElement(ele);
                    }
                }
            }
        }
        else {
            delegate.parseCustomElement(root);
        }
    }

    private void parseDefaultElement(Element ele, BeanDefinitionParserDelegate delegate) {
    }

    private void preProcessXml(Element root) {

    }


    public BeanDefinitionParserDelegate createDelegate(XmlReaderContext type, Element root, BeanDefinitionParserDelegate parserDelegate) {
        return null;
    }

    private XmlReaderContext getReaderContext() {
        return null;
    }

}
