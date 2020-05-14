package prt.navitruck.back.app.service.truck;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prt.navitruck.back.app.model.entity.User;
import prt.navitruck.back.app.model.entity.truck.TruckUser;

public interface TruckUserService {
    int reset(long ID);
    TruckUser save(TruckUser truckUser);
    TruckUser getUser(Long id);
}
