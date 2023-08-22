package data.bean;

import org.springframework.context.annotation.Scope;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

@ManagedBean(eager = true)
@Scope
public class TestBean {

    @ManagedProperty(value = "#{message}")
    private Message messageBean;
    private String message;

    public TestBean() {
        System.out.println("класс TestBean");
    }

    public String getMessage() {

        if (messageBean != null) {
            message = messageBean.getMessage();
        }
        return message;
    }

    public void setMessageBean(Message message) {
        this.messageBean = message;
    }
}
