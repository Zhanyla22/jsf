package data.bean;

import org.springframework.context.annotation.Scope;

import javax.faces.bean.ManagedBean;

@ManagedBean(eager = true)
@Scope
public class Message {

    private String message = "Hello World!";

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
