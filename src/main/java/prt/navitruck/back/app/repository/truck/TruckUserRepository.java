package prt.navitruck.back.app.repository.truck;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import prt.navitruck.back.app.model.entity.truck.Truck;
import prt.navitruck.back.app.model.entity.truck.TruckUser;
import prt.navitruck.back.app.repository.abstr.AbstractRepository;

public interface TruckUserRepository extends AbstractRepository<TruckUser, Long> {

    @Modifying
    @Query("update TruckUser t set t.password = null, t.confirmed = false where t.id = :ID")
    int reset(@Param("ID") long id);

    @Modifying
    @Query("update TruckUser t set t.truck = :truck where t.id = :driverID")
    int assign(@Param("truck") Truck truck, @Param("driverID") long driverID);

}
