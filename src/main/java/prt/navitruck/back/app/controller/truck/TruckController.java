package prt.navitruck.back.app.controller.truck;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prt.navitruck.back.app.controller.abstr.AbstractController;
import prt.navitruck.back.app.model.entity.task.Task;
import prt.navitruck.back.app.model.entity.truck.Truck;
import prt.navitruck.back.app.model.entity.truck.TruckParams;
import prt.navitruck.back.app.model.entity.truck.TruckUser;
import prt.navitruck.back.app.model.response.ResponseDTO;
import prt.navitruck.back.app.repository.abstr.AbstractRepository;
import prt.navitruck.back.app.utils.Constants;

@RestController
@RequestMapping("/truck")
@CrossOrigin("http://localhost:1841")
public class TruckController extends AbstractController<Truck, Long> {

    public TruckController(AbstractRepository<Truck, Long> repository) {
        super(repository);
    }

    @PutMapping("/save")
    public ResponseEntity save(@RequestParam int make,
                               @RequestParam int model,
                               @RequestParam Constants.BodyType bodyType,
                               @RequestParam Constants.TruckType truckType,
                               @RequestParam int year,
                               @RequestParam int truckParam,
                               @RequestParam String paramName,
                               @RequestParam boolean newParam,
                               @RequestParam int width,
                               @RequestParam int height,
                               @RequestParam int length,
                               @RequestParam int maxWeight
                               ) {
        System.out.println("save truck");


        return ResponseEntity.ok(ResponseDTO.builder().success(true).build());
    }

    @PutMapping("/test")
    public ResponseEntity test(@RequestBody Truck truck
    ) {
        System.out.println("save save test ");


        return ResponseEntity.ok(ResponseDTO.builder().success(true).build());
    }
}
