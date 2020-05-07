package prt.navitruck.back.app.model.entity;

import lombok.*;
import prt.navitruck.back.app.model.entity.abstr.AbstractEntity;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "test2")
public class TestTest extends AbstractEntity {

    @Column(name = "test", nullable = true)
    private String test;
}
