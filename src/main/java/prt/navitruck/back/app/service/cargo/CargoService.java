package prt.navitruck.back.app.service.cargo;

import org.json.JSONObject;
import org.springframework.scheduling.annotation.Async;
import prt.navitruck.back.app.model.entity.cargo.Cargo;

import java.util.List;
import java.util.concurrent.CompletableFuture;


public interface CargoService {

    Cargo getCargo(Long id);
    Cargo update(Cargo carg);
    List<Cargo> findAll();
    @Async
    CompletableFuture<Cargo> saveCargoAsync(Cargo cargo);
    Cargo saveCargo(Cargo cargo);
    JSONObject buildJsonFromCargo(Cargo cargo);
}
