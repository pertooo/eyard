package prt.navitruck.back.app.controller.truck;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prt.navitruck.back.app.controller.abstr.AbstractController;
import prt.navitruck.back.app.model.entity.truck.TruckUser;
import prt.navitruck.back.app.model.response.ResponseDTO;
import prt.navitruck.back.app.repository.abstr.AbstractRepository;
import prt.navitruck.back.app.service.truck.TruckUserService;

@RestController
@RequestMapping("truck_user")
public class TruckUserController extends AbstractController<TruckUser, Long> {

    public TruckUserController(AbstractRepository<TruckUser, Long> repository) {
        super(repository);
    }

    @Autowired
    private TruckUserService truckUserService;

    @PutMapping("/save")
    public ResponseEntity save(@RequestParam String username,
                               @RequestParam boolean team,
                               @RequestParam boolean available) {
        System.out.println("save truckUser");

        TruckUser truckUser = new TruckUser(null, username.toUpperCase(), null, team, available, false, true);

        TruckUser savedObject = truckUserService.save(truckUser);
        if (savedObject != null) {
            return ResponseEntity.ok(ResponseDTO.builder().success(true).build());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/reset")
    public ResponseEntity reset(@RequestParam int ID) {
        int ret = 0;
        try{
            ret = truckUserService.reset(ID);
            if(ret>0)
                return ResponseEntity.ok(ResponseDTO.builder().success(true).build());
        }catch (Exception e){
            e.printStackTrace();
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/assign")
    public ResponseEntity assignDriverToTruck(@RequestParam long truckID, @RequestParam long driverID) {
        try {
            return ResponseEntity.ok(ResponseDTO.builder().success(truckUserService.assign(truckID, driverID)).build());
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.notFound().build();
    }
}
