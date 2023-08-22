package data.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Scope;

import javax.faces.bean.ManagedBean;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ManagedBean(name = "loginDto")
@Scope
public class AddDelayDto {

    Long userId;

    LocalDate date;

    Integer delayInMin;

    Long money;
}