package prt.navitruck.back.app.serviceImpl.cargo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prt.navitruck.back.app.errors.EntityNotFoundException;
import prt.navitruck.back.app.model.entity.cargo.Cargo;
import prt.navitruck.back.app.model.entity.cargo.CargoUserJoin;
import prt.navitruck.back.app.repository.cargo.CargoUserJoinRepository;
import prt.navitruck.back.app.service.cargo.CargoUserJoinService;

@Service
public class CargoUserJoinServiceImpl implements CargoUserJoinService {

    @Autowired
    private CargoUserJoinRepository cargoUserJoinRepository;

    @Override
    public CargoUserJoin getOne(long id) {
        return cargoUserJoinRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(CargoUserJoin.class));
    }

    @Override
    public CargoUserJoin getAssignedByCargo(Cargo cargo) {
        return cargoUserJoinRepository.findByCargoAndAssigned(cargo, true);
    }

    @Override
    public CargoUserJoin save(CargoUserJoin cargoUserJoin) {
        return cargoUserJoinRepository.save(cargoUserJoin);
    }

    @Override
    @Transactional
    public CargoUserJoin update(CargoUserJoin cargoUserJoin) {
        return cargoUserJoinRepository.save(cargoUserJoin);
    }

    @Override
    @Transactional
    public int assignUserToCargo(long userId, long cargoId) {
        return cargoUserJoinRepository.setAssigned(userId, cargoId);
    }


}
