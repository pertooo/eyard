package prt.navitruck.back.app.model.entity.truck;

import lombok.*;
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

    @Column(name = "make", nullable = false)
    private String make;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "truck_type", nullable = false)
    private Constants.TruckType truckType;

    @Column(name = "body_type", nullable = false)
    private Constants.BodyType bodyType;

    @Column(name = "year", nullable = false)
    private int year;

    @ManyToOne
    @JoinColumn
    private TruckParams truckParams;

}
