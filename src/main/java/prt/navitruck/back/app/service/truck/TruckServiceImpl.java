package prt.navitruck.back.app.service.truck;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prt.navitruck.back.app.model.dto.ComboDTO;
import prt.navitruck.back.app.model.dto.TruckDTO;
import prt.navitruck.back.app.model.entity.Dictionary;
import prt.navitruck.back.app.model.entity.truck.Truck;
import prt.navitruck.back.app.model.entity.truck.TruckParams;
import prt.navitruck.back.app.repository.truck.TruckParamsRepository;
import prt.navitruck.back.app.repository.truck.TruckRepository;

import java.util.List;

@Service
public class TruckServiceImpl implements TruckService{

    @Autowired
    TruckRepository truckRepository;

    @Autowired
    TruckParamsRepository truckParamsRepository;

    @Override
    public boolean save(TruckDTO truckDTO) {

        try{

            if(truckDTO.isNewParam()){ // add new Truck Param

                TruckParams truckParams = truckParamsRepository.save(
                        new TruckParams(truckDTO.getParamName(), truckDTO.getMaxWeight(),
                        truckDTO.getLength(), truckDTO.getWidth(), truckDTO.getHeight()));

                Truck truck = new Truck(new Dictionary(truckDTO.getMake()), new Dictionary(truckDTO.getModel()),
                        truckDTO.getTruckType(),truckDTO.getBodyType(), truckDTO.getYear(), truckParams);

                truckRepository.save(truck);
            }else{
                Truck truck = new Truck(new Dictionary(truckDTO.getMake()), new Dictionary(truckDTO.getModel()),
                        truckDTO.getTruckType(), truckDTO.getBodyType(), truckDTO.getYear(),
                        new TruckParams(truckDTO.getTruckParam()));

                truckRepository.save(truck);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public List<ComboDTO> getCombo() {
        return truckRepository.getCombo();
    }
}
