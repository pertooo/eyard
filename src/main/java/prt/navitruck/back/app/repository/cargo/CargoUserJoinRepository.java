package prt.navitruck.back.app.repository.cargo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import prt.navitruck.back.app.model.entity.cargo.Cargo;
import prt.navitruck.back.app.model.entity.cargo.CargoUserJoin;
import prt.navitruck.back.app.repository.abstr.AbstractRepository;

public interface CargoUserJoinRepository extends AbstractRepository<CargoUserJoin, Long> {


    @Modifying
    @Query("update CargoUserJoin t set t.assigned = true where t.user.id = :userId and t.cargo.id = :cargoId")
    int setAssigned(@Param("userId") Long userId, @Param("cargoId") Long cargoId);

    CargoUserJoin findByCargoAndAssigned(Cargo cargo, Boolean assigned);

}
