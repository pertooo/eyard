package prt.navitruck.back.app.model.entity.truck;

import lombok.*;
import prt.navitruck.back.app.model.entity.abstr.AbstractEntity;
import prt.navitruck.back.app.utils.Constants;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "truck_params")
public class TruckParams extends AbstractEntity {

    @Column(name = "param_name", nullable = true)
    private String paramName;

    @Column(name = "max_weight", nullable = true)
    private int maxWeight;

    @Column(name = "length", nullable = true)
    private int length;

    @Column(name = "width", nullable = true)
    private int width;

    @Column(name = "height", nullable = true)
    private int height;

    public TruckParams(long id){
        this.id = id;
    }

}
