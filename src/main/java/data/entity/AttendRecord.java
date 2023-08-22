package data.entity;

import data.entity.base.BaseEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "attendance")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AttendRecord extends BaseEntity {

    @Column(name = "delay_in_min")
    Integer delayInMin;

    @Column(name = "attend_date")
    LocalDateTime attendDate;

    @Column(name = "streak")
    Byte streak;

    @ManyToOne(optional = false)
    @JoinColumn(columnDefinition = "user_id",
            referencedColumnName = "id")
    UserEntity userEntity;
}
