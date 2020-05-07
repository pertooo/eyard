package prt.navitruck.back.app.service.truck;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prt.navitruck.back.app.model.entity.truck.TruckParams;
import prt.navitruck.back.app.repository.truck.TruckParamsRepository;

import java.util.List;

@Service
public class TruckParametersServiceImpls implements TruckParametersService {

    @Autowired
    TruckParamsRepository truckParamsRepository;

    @Override
    public List<TruckParams> getAll() {
        return truckParamsRepository.findAll();
    }
}
