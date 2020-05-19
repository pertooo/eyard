package prt.navitruck.back.app.repository.cargo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import prt.navitruck.back.app.model.entity.cargo.Cargo;
import prt.navitruck.back.app.model.entity.cargo.CargoTruckUserResponse;
import prt.navitruck.back.app.repository.abstr.AbstractRepository;

public interface CargoUserJoinRepository extends AbstractRepository<CargoTruckUserResponse, Long> {

   // CargoTruckUserResponse findByCargoAndAssigned(Cargo cargo, Boolean assigned);

}
