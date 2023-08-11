package data.entity;

import data.entity.base.BaseEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "product")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductEntity extends BaseEntity {

    @Column(name = "name")
    String name;

    @Column(name = "quantity")
    Long quantity;

    @Column(name = "price")
    Double price;
}
