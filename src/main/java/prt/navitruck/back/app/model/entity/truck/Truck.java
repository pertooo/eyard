package prt.navitruck.back.app.model.entity.truck;

import lombok.*;
import prt.navitruck.back.app.model.entity.Dictionary;
import prt.navitruck.back.app.model.entity.abstr.AbstractEntity;
import prt.navitruck.back.app.utils.Constants;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "trucks")
public class Truck extends AbstractEntity {

    @ManyToOne
    @JoinColumn
    private Dictionary make;

    @ManyToOne
    @JoinColumn
    private Dictionary model;

    @Enumerated(EnumType.STRING)
    @Column(name = "truck_type", nullable = false)
    private Constants.TruckType truckType;

    @Enumerated(EnumType.STRING)
    @Column(name = "body_type", nullable = false)
    private Constants.BodyType bodyType;

    @Column(name = "year", nullable = false)
    private int year;

    @Transient
    private boolean newParam;

    @ManyToOne
    @JoinColumn
    private TruckParams truckParam;

}
