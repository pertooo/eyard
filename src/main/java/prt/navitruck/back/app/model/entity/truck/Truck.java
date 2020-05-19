package prt.navitruck.back.app.model.entity.truck;

import lombok.*;
import org.springframework.lang.Nullable;
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

    @Column(name = "serial_number", nullable = false)
    private String serialNum;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "make_id")
    private Dictionary make;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "model_id")
    private Dictionary model;

    @Enumerated(EnumType.STRING)
    @Column(name = "truck_type", nullable = false)
    private Constants.TruckType truckType;

    @Enumerated(EnumType.STRING)
    @Column(name = "body_type", nullable = false)
    private Constants.BodyType bodyType;

    @Column(name = "year", nullable = false)
    private int year;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE}) //  CascadeType.ALL ახალ ობიეკტზე  მუშაობს
    @JoinColumn(name = "truck_param_id")
    private TruckParams truckParam;

}
