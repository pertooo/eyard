package prt.navitruck.back.app.service.cargo;

import prt.navitruck.back.app.model.entity.cargo.Cargo;
import prt.navitruck.back.app.model.entity.cargo.CargoTruckUserResponse;

public interface CargoTruckUserResponseService {
    CargoTruckUserResponse getOne(long id);
    CargoTruckUserResponse getAssignedByCargo(Cargo cargo);
    CargoTruckUserResponse save(CargoTruckUserResponse cargoTruckUserResponse);
    CargoTruckUserResponse update(CargoTruckUserResponse cargoTruckUserResponse);
    int assignUserToCargo(long userId, long cargoId);
}
