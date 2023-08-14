package data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope
public class HelloBean {

    @Autowired
    private MsgService msgService;

    public String getMsg() {
        return msgService.getMsg();
    }
}
