package prt.navitruck.back.app.model.entity.truck;

import lombok.*;
import org.springframework.lang.Nullable;
import prt.navitruck.back.app.model.entity.abstr.AbstractEntity;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "truck_user")
public class TruckUser extends AbstractEntity {


    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = true)
    private String password;

    @Column(name = "team", nullable = true)
    private boolean team;

    @Column(name = "available", nullable = false)
    private boolean available;

    @Column(name = "confirmed", nullable = false)
    private boolean confirmed;

    @Column(name = "active", nullable = false)
    private boolean active;

    @OneToOne
    @JoinColumn
    @Nullable
    private Truck truck;
}
