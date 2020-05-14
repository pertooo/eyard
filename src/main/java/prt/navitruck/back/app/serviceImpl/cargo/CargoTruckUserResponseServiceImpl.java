package prt.navitruck.back.app.serviceImpl.cargo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prt.navitruck.back.app.errors.EntityNotFoundException;
import prt.navitruck.back.app.model.entity.cargo.Cargo;
import prt.navitruck.back.app.model.entity.cargo.CargoTruckUserResponse;
import prt.navitruck.back.app.repository.cargo.CargoUserJoinRepository;
import prt.navitruck.back.app.service.cargo.CargoTruckUserResponseService;

@Service
public class CargoTruckUserResponseServiceImpl implements CargoTruckUserResponseService {

    @Autowired
    private CargoUserJoinRepository cargoUserJoinRepository;

    @Override
    public CargoTruckUserResponse getOne(long id) {
        return cargoUserJoinRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(CargoTruckUserResponse.class));
    }

    @Override
    public CargoTruckUserResponse getAssignedByCargo(Cargo cargo) {
        return cargoUserJoinRepository.findByCargoAndAssigned(cargo, true);
    }

    @Override
    public CargoTruckUserResponse save(CargoTruckUserResponse cargoTruckUserResponse) {
        return cargoUserJoinRepository.save(cargoTruckUserResponse);
    }

    @Override
    @Transactional
    public CargoTruckUserResponse update(CargoTruckUserResponse cargoTruckUserResponse) {
        return cargoUserJoinRepository.save(cargoTruckUserResponse);
    }

    @Override
    @Transactional
    public int assignUserToCargo(long userId, long cargoId) {
        return cargoUserJoinRepository.setAssigned(userId, cargoId);
    }


}
