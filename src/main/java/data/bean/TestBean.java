package data.bean;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.context.annotation.SessionScope;

import javax.faces.bean.ManagedBean;

@ManagedBean
@SessionScope
public class TestBean {

    @Getter
    @Setter
    private String data;

    public void greet() {
        System.out.println(data);;
    }
}
