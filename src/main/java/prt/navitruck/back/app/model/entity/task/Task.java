package prt.navitruck.back.app.model.entity.task;

import lombok.*;
import prt.navitruck.back.app.model.entity.abstr.AbstractEntity;
import prt.navitruck.back.app.model.entity.embeddable.DateCreatedUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "task")
public class Task extends AbstractEntity {


    @Column(name = "address_from", nullable = false)
    private String addressFrom;

    @Column(name = "address_to", nullable = false)
    private String addressTo;

    @Column(name = "pickup_time", nullable = true)
    private LocalDateTime pickupTime;

    @Column(name = "unload_time", nullable = true)
    private LocalDateTime unloadTime;

    @Column(name = "weight", nullable = false)
    private double weight;

    @Column(name = "fee", nullable = true, columnDefinition = "Decimal(10,2)")
    private double fee;


}
