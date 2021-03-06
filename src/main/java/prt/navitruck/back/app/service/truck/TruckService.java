package prt.navitruck.back.app.service.truck;

import prt.navitruck.back.app.model.dto.ComboDTO;
import prt.navitruck.back.app.model.dto.TruckDTO;
import prt.navitruck.back.app.model.entity.truck.Truck;

import java.util.List;

public interface TruckService {
    boolean save(TruckDTO truckDTO);
    List<ComboDTO> getCombo();
    Truck getOne(long id);

}
