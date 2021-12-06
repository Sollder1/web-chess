package de.sollder1.webchess.backend.api;

public class GreetingBean {

    private String beanValue;

    public GreetingBean(String beanValue) {
        this.beanValue = beanValue;
    }

    public String getBeanValue() {
        return beanValue;
    }

    public void setBeanValue(String beanValue) {
        this.beanValue = beanValue;
    }
}
