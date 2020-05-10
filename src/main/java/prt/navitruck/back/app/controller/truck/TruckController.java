package prt.navitruck.back.app.controller.truck;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prt.navitruck.back.app.controller.abstr.AbstractController;
import prt.navitruck.back.app.model.dto.TruckDTO;
import prt.navitruck.back.app.model.entity.truck.Truck;
import prt.navitruck.back.app.model.response.ResponseDTO;
import prt.navitruck.back.app.repository.abstr.AbstractRepository;
import prt.navitruck.back.app.service.truck.TruckService;

@RestController
@RequestMapping("/truck")
@CrossOrigin("http://localhost:1841")
public class TruckController extends AbstractController<Truck, Long> {

    public TruckController(AbstractRepository<Truck, Long> repository) {
        super(repository);
    }

    @Autowired
    TruckService truckService;

    @PutMapping("/save")
    public ResponseEntity save(@RequestBody TruckDTO truck) {
        System.out.println("save truck");

        boolean ret = truckService.save(truck);

        return ResponseEntity.ok(ResponseDTO.builder().success(ret).build());
    }


}
