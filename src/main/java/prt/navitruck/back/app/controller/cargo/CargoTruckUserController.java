package prt.navitruck.back.app.controller.cargo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import prt.navitruck.back.app.controller.abstr.AbstractController;
import prt.navitruck.back.app.model.entity.cargo.Cargo;
import prt.navitruck.back.app.model.entity.cargo.CargoTruckUserResponse;
import prt.navitruck.back.app.model.entity.truck.TruckUser;
import prt.navitruck.back.app.model.response.ResponseDTO;
import prt.navitruck.back.app.repository.cargo.CargoUserJoinRepository;
import prt.navitruck.back.app.service.cargo.CargoService;
import prt.navitruck.back.app.service.cargo.CargoTruckUserResponseService;
import prt.navitruck.back.app.service.truck.TruckUserService;
import prt.navitruck.back.app.utils.Constants;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/cargo_truck_user")
public class CargoTruckUserController extends AbstractController<CargoTruckUserResponse, Long> {

    @Autowired
    public CargoTruckUserController(CargoUserJoinRepository repository) {
        super(repository);
    }

    private static int attempt = 0;
    private final int maxAttempts = 3;
    private final int threadSleepDelay = 15;

    @Autowired
    private CargoService cargoService;

    @Autowired
    private TruckUserService truckUserService;

    @Autowired
    private CargoTruckUserResponseService cargoTruckUserResponseService;

    @PostMapping("/accept")
    public ResponseEntity accept(@RequestParam long cargoId,
                                 @RequestParam long userId,
                                 @RequestParam double offer,
                                 @RequestParam boolean accepted) {

        Cargo cargo = cargoService.getCargo(cargoId);
        TruckUser truckUser = truckUserService.getUser(userId);
        System.out.println("Task task - "+ cargo.getDeliveryTo());

        List<String> errors = null;

        Constants.CargoTruckUserResponse userResponse = Constants.CargoTruckUserResponse.REFUSED;
        if(accepted)
            userResponse = Constants.CargoTruckUserResponse.ACCEPTED;

        if(cargo !=null && truckUser!=null){
            try{
                cargoTruckUserResponseService.save(new CargoTruckUserResponse(cargo, truckUser, offer, userResponse));
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        }

        CargoTruckUserResponse cargoTruckUserResponse = null;

        try{
            cargoTruckUserResponse = getAssignedAfterDelay(cargo);
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }


        if(cargoTruckUserResponse !=null){ //exists and return success

            if(true) {//TODO check if taskUserJoin is for current user
                return ResponseEntity.ok(ResponseDTO.builder().success(true).content(Constants.CargoStatusObj.ASSIGNED).build());
            }else{
                return ResponseEntity.ok(ResponseDTO.builder().success(true).content(Constants.CargoStatusObj.ASSIGNED_TO_OTHER).build());
            }


        }else{ //return response needs more time
            return ResponseEntity.ok(ResponseDTO.builder().success(true).content(Constants.CargoStatusObj.NOT_ASSIGNED).build());
        }

    }

    private CargoTruckUserResponse getAssignedAfterDelay(Cargo cargo){
        threadSleep();

        CargoTruckUserResponse cargoTruckUserResponse = cargoTruckUserResponseService.getAssignedByCargo(cargo);

        if(cargoTruckUserResponse !=null){
            return cargoTruckUserResponse;
        }else{ // wait for more  until 5 attempts
            if(attempt<maxAttempts){
                attempt++;
                getAssignedAfterDelay(cargo);
            }
        }
        return null;
    }

    private void threadSleep(){
        try{
            TimeUnit.SECONDS.sleep(threadSleepDelay);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @PostMapping("/update_status")
    public ResponseEntity updateStatus(@RequestParam long cargoId,
                                       @RequestParam long userId) {

        System.out.println("updateStatus");

        return ResponseEntity.ok(ResponseDTO.builder().success(true).build());
    }


}
