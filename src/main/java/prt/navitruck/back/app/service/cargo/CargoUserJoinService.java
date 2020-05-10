package prt.navitruck.back.app.service.cargo;

import prt.navitruck.back.app.model.entity.cargo.Cargo;
import prt.navitruck.back.app.model.entity.cargo.CargoUserJoin;

public interface CargoUserJoinService {

    CargoUserJoin getOne(long id);
    CargoUserJoin getAssignedByCargo(Cargo cargo);
    CargoUserJoin save(CargoUserJoin cargoUserJoin);
    CargoUserJoin update(CargoUserJoin cargoUserJoin);
    int assignUserToCargo(long userId, long cargoId);

}
