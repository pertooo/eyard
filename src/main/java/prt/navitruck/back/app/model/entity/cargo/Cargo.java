package prt.navitruck.back.app.model.entity.cargo;

import lombok.*;
import org.hibernate.mapping.ToOne;
import org.json.JSONObject;
import prt.navitruck.back.app.model.entity.abstr.AbstractEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import com.google.gson.*;
import prt.navitruck.back.app.model.entity.truck.TruckUser;
import prt.navitruck.back.app.utils.Constants;

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
    private int weight;

    @Column(name = "fee", nullable = true, columnDefinition = "Decimal(10,2)")
    private double fee;

    @Column(name = "miles", nullable = true)
    private int miles;

    @Column(name = "pieces")
    private int pieces;

    @Column(name = "dims")
    private String dims;

    @OneToOne
    @JoinColumn(name = "assigned_to", nullable = true)
    private TruckUser truckUser;

    @Column(name = "user_offer", nullable = true, columnDefinition = "Decimal(10,2)")
    private double userOffer;

    @Column(name = "accept_timestamp")
    private LocalDateTime acceptedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Constants.CargoStatus status;

    @Transient
    private String pickupTimeStr;

    @Transient
    private String unloadTimeStr;

    @Transient
    private boolean sendImmediately;

    public Cargo(JSONObject obj){
        this.pickUpAt = obj.getString("Pick-up at");
        this.deliveryTo = obj.getString("Deliver to");

        this.deliveryDate = null;//obj.getString("Delivery date (EST)");
        this.pickUpDate = null;//obj.getString("Pick-up date (EST)");

        this.miles = Integer.parseInt(obj.getString("Miles"));
        this.weight = Integer.parseInt(obj.getString("Weight"));
        this.pieces = Integer.parseInt(obj.getString("Pieces"));
        this.dims = obj.getString("Dims");
        this.status = Constants.CargoStatus.NEW;
    }
}
