package com.xuanhui.spring.analysis;

public class DefaultListableBeanFactory  extends org.springframework.beans.factory.support.DefaultListableBeanFactory {

    private String serializationId;

    public String getSerializationId() {
        return serializationId;
    }

    public void setSerializationId(String serializationId) {
        this.serializationId = serializationId;
    }
}
