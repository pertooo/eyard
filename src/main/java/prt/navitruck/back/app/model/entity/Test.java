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
@Table(name = "test")
public class Test extends AbstractEntity {

    @ManyToOne
    @JoinColumn
    private Dictionary make;

    @Column(name = "name", nullable = true)
    private String name;
}
