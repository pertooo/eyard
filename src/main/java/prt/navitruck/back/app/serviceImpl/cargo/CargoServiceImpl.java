package prt.navitruck.back.app.serviceImpl.cargo;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prt.navitruck.back.app.model.entity.cargo.Cargo;
import prt.navitruck.back.app.repository.cargo.CargoRepository;
import prt.navitruck.back.app.service.cargo.CargoService;
import prt.navitruck.back.app.utils.Constants;

import java.util.List;

@Service
public class CargoServiceImpl implements CargoService {

    @Autowired
    private CargoRepository cargoRepository;


    @Override
    public Cargo getCargo(Long id) {
        return cargoRepository.findById(id).orElse(null);
    }

    @Override
    public Cargo update(Cargo cargo) {
        return cargoRepository.save(cargo);
    }

    @Override
    public List<Cargo> findAll() {
        return cargoRepository.findAll();
    }

    @Override
    public Cargo saveCargo(Cargo cargo) {
        return cargoRepository.save(cargo);
    }

    @Override
    public JSONObject buildJsonFromCargo(Cargo cargo) {
        JSONObject body = new JSONObject();

        try {
            //  body.put("to", "/topics/" + TOPIC);
            body.put("condition", Constants.empty_string);
            body.put("priority", "high");

            JSONObject notification = new JSONObject();
            notification.put("title", "JSA Notification");
            notification.put("body", "Happy Message!");

            JSONObject data = new JSONObject();

            data.put("addressFrom", cargo.getPickUpDate());
            data.put("addressTo", cargo.getDeliveryTo());

            data.put("loadTime", cargo.getPickupTimeStr());
            data.put("unloadTime", cargo.getUnloadTimeStr());

            data.put("price", cargo.getFee());
            data.put("weight", cargo.getWeight());

            //  body.put("notification", notification);
            body.put("data", data);

        }catch (Exception e){
            e.printStackTrace();
        }
        return body;
    }
}
