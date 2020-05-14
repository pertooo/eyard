package prt.navitruck.back.app.repository.cargo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import prt.navitruck.back.app.model.entity.cargo.Cargo;
import prt.navitruck.back.app.model.entity.cargo.CargoTruckUserResponse;
import prt.navitruck.back.app.repository.abstr.AbstractRepository;

public interface CargoUserJoinRepository extends AbstractRepository<CargoTruckUserResponse, Long> {


    @Modifying
    @Query("update CargoTruckUserResponse t set t.assigned = true where t.user.id = :userId and t.cargo.id = :cargoId")
    int setAssigned(@Param("userId") Long userId, @Param("cargoId") Long cargoId);

    CargoTruckUserResponse findByCargoAndAssigned(Cargo cargo, Boolean assigned);

}
