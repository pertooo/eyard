package prt.navitruck.back.app.model.entity.cargo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import prt.navitruck.back.app.model.entity.User;
import prt.navitruck.back.app.model.entity.abstr.AbstractEntity;
import prt.navitruck.back.app.model.entity.truck.TruckUser;
import prt.navitruck.back.app.utils.Constants;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"cargo_id", "user_id"}))
public class CargoTruckUserResponse extends AbstractEntity {

    public CargoTruckUserResponse(Cargo cargo, TruckUser user){
        this.cargo = cargo;
        this.user = user;
    }

    @ManyToOne
    @JoinColumn
    private Cargo cargo;

    @ManyToOne
    @JoinColumn
    private TruckUser user;

    @Column(name = "user_offer")
    private double userOffer;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_response", nullable = false)
    private Constants.CargoTruckUserResponse userResponse;

}
