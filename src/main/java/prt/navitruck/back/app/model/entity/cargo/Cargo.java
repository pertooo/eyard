package prt.navitruck.back.app.model.entity.cargo;

import lombok.*;
import prt.navitruck.back.app.model.entity.abstr.AbstractEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cargo")
public class Cargo extends AbstractEntity {


    @Column(name = "pickup_at", nullable = false)
    private String pickUpAt;

    @Column(name = "puckup_date", nullable = true)
    private LocalDateTime pickUpDate;

    @Column(name = "delivery_to", nullable = false)
    private String deliveryTo;

    @Column(name = "delivery_date", nullable = true)
    private LocalDateTime deliveryDate;

    @Column(name = "weight", nullable = false)
    private double weight;

    @Column(name = "fee", nullable = true, columnDefinition = "Decimal(10,2)")
    private double fee;

    @Column(name = "miles", nullable = true, columnDefinition = "Decimal(10,2)")
    private double miles;

    @Column(name = "pieces")
    private int pieces;

    @Transient
    private String pickupTimeStr;

    @Transient
    private String unloadTimeStr;

    @Transient
    private boolean sendImmediately;
}
