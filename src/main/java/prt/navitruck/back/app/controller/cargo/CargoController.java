package prt.navitruck.back.app.controller.cargo;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prt.navitruck.back.app.controller.abstr.AbstractController;
import prt.navitruck.back.app.model.response.ResponseDTO;
import prt.navitruck.back.app.model.entity.cargo.Cargo;
import prt.navitruck.back.app.model.entity.cargo.CargoTruckUserResponse;
import prt.navitruck.back.app.repository.cargo.CargoRepository;
import prt.navitruck.back.app.service.cargo.CargoService;
import prt.navitruck.back.app.service.cargo.CargoTruckUserResponseService;
import prt.navitruck.back.app.service.notification.AndroidPushNotificationsService;
import prt.navitruck.back.app.service.user.UserService;
import prt.navitruck.back.app.utils.Constants;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/cargo")
public class CargoController extends AbstractController<Cargo, Long> {

    @Autowired
    private CargoService cargoService;

    @Autowired
    private UserService userService;

    @Autowired
    private CargoTruckUserResponseService cargoTruckUserResponseService;

    @Autowired
    AndroidPushNotificationsService androidPushNotificationsService;

    @Autowired
    public CargoController(CargoRepository repository) {
        super(repository);
    }

    @PutMapping("/save")
    public ResponseEntity save(@RequestBody Cargo cargo) {
        Cargo savedObject = cargoService.saveCargo(cargo);

        if(cargo.isSendImmediately()) { // send notification
            try {
                JSONObject body = cargoService.buildJsonFromCargo(savedObject);
                HttpEntity<String> request = new HttpEntity<>(body.toString());

                CompletableFuture<String> pushNotification = androidPushNotificationsService.send(request);
                CompletableFuture.allOf(pushNotification).join();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        if (savedObject != null) {
            return ResponseEntity.ok(ResponseDTO.builder().success(true).build());
        }
        return ResponseEntity.notFound().build();
    }

    //    @RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT },
//            consumes = { "multipart/form-data" } , path = "update_status")
//    public ResponseEntity updateStatus(@RequestParam("files") MultipartFile[] files,
//                                       @RequestParam long taskId,
//                                       @RequestParam long userId) {
//
//        System.out.println("updateStatus");
//
//        return ResponseEntity.ok(ResponseDTO.builder().success(true).build());
//    }



    @PostMapping("/assignById")
    public ResponseEntity accept(@RequestParam long id) {

        try{
            CargoTruckUserResponse cargoTruckUserResponse = cargoTruckUserResponseService.getOne(id);

            if(cargoTruckUserResponse !=null){

                Cargo cargo = cargoService.getCargo(cargoTruckUserResponse.getCargo().getId());
                cargo.setAcceptedAt(LocalDateTime.now());
                cargo.setUserOffer(cargoTruckUserResponse.getUserOffer());
                cargo.setStatus(Constants.CargoStatus.ACTIVE);

                Cargo c = cargoService.update(cargo);
                return ResponseEntity.ok(ResponseDTO.builder().success(true).build());

            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return ResponseEntity.ok(ResponseDTO.builder().success(false).build());
    }

}
