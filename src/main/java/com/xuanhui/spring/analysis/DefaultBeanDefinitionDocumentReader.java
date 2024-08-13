package com.xuanhui.spring.analysis;

import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.parsing.BeanComponentDefinition;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;
import org.springframework.beans.factory.xml.XmlReaderContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.annotation.Nullable;

/**
 * 1. xml 配置中默认标签的解析： import bean beans alias
 * <p>
 * 1.1  bean 标签的解析  processBeanDefinition
 * 2. 注册beanDefinitions
 */
public class DefaultBeanDefinitionDocumentReader extends AbstractBeanDefinitionReader {
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
                String[] specifiedProfiles = StringUtils.tokenizeToStringArray(profileSpec, BeanDefinitionParserDelegate.MULTI_VALUE_ATTRIBUTE_DELIMITERS);


                if (getReaderContext().getEnvironment().acceptsProfiles(specifiedProfiles)) {
                    return;
                }
            }

        }

        // refresh  obtainBeanFactory freshBeanFactory loadBeanDefinition  registerBeanDefinition

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
                    } else {
                        delegate.parseCustomElement(ele);
                    }
                }
            }
        } else {
            delegate.parseCustomElement(root);
        }
    }

    private void parseDefaultElement(Element ele, BeanDefinitionParserDelegate delegate) {
        if (delegate.nodeNameEquals(ele, "import")) {
            this.importBeanDefinitionResource(ele);
        } else if (delegate.nodeNameEquals(ele, "alias")) {
            this.processAliasRegistration(ele);
        } else if (delegate.nodeNameEquals(ele, "bean")) {
            this.processBeanDefinition(ele, delegate);
        } else if (delegate.nodeNameEquals(ele, "beans")) {
            this.doRegisterBeanDefinitions(ele);
        }
    }

    // bean 标签的注册以及解析
    private void processBeanDefinition(Element ele, BeanDefinitionParserDelegate delegate) {

        // 解析xml 元素，得到  配置文件中的属性值 。如 class, name, id 等
        BeanDefinitionHolder bdHolder = delegate.parseBeanDefinitionElement(ele);

        if (bdHolder != null) {
            // 解析自定义标签
            delegate.decorateBeanDefinitionIfRequired(ele, bdHolder);

            // 对解析后的数据进行注册
            BeanDefinitionReaderUtils.registerBeanDefinition(bdHolder, this.getReaderContext().getRegistry());
        }

        // 发出响应事件，通知相关监听器
        this.getReaderContext().fireComponentRegistered(new BeanComponentDefinition(bdHolder));


    }

    private void processAliasRegistration(Element ele) {

    }

    private void importBeanDefinitionResource(Element ele) {

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
