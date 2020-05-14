package prt.navitruck.back.app.service.cargo;

import org.json.JSONObject;
import prt.navitruck.back.app.model.entity.cargo.Cargo;

import java.util.List;


public interface CargoService {

    Cargo getCargo(Long id);
    Cargo update(Cargo carg);
    List<Cargo> findAll();
    Cargo saveCargo(Cargo cargo);
    JSONObject buildJsonFromCargo(Cargo cargo);
}
