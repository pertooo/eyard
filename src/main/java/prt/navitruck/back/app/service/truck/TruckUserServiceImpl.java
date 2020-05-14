package prt.navitruck.back.app.service.truck;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prt.navitruck.back.app.model.entity.User;
import prt.navitruck.back.app.model.entity.truck.TruckUser;
import prt.navitruck.back.app.repository.truck.TruckUserRepository;

@Service
public class TruckUserServiceImpl implements TruckUserService{

    @Autowired
    TruckUserRepository truckUserRepository;

    @Override
    @Transactional
    public int reset(long ID) {
        return truckUserRepository.reset(ID);
    }

    @Override
    public TruckUser save(TruckUser truckUser) {
        return truckUserRepository.save(truckUser);
    }

    @Override
    public TruckUser getUser(Long id) {
        return truckUserRepository.getOne(id);
    }
}
