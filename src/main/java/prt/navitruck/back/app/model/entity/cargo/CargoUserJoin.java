package prt.navitruck.back.app.model.entity.cargo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import prt.navitruck.back.app.model.entity.User;
import prt.navitruck.back.app.model.entity.abstr.AbstractEntity;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"cargo_id", "user_id"}))
public class CargoUserJoin extends AbstractEntity {

    public CargoUserJoin(Cargo cargo, User user){
        this.cargo = cargo;
        this.user = user;
    }

    @ManyToOne
    @JoinColumn
    private Cargo cargo;

    @ManyToOne
    @JoinColumn
    private User user;

    @Column(name = "assigned_to_user", nullable = false)
    private boolean assigned;

}
