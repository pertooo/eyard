package prt.navitruck.back.app.controller.cargo;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prt.navitruck.back.app.controller.abstr.AbstractController;
import prt.navitruck.back.app.model.response.ResponseDTO;
import prt.navitruck.back.app.model.entity.User;
import prt.navitruck.back.app.model.entity.cargo.Cargo;
import prt.navitruck.back.app.model.entity.cargo.CargoUserJoin;
import prt.navitruck.back.app.repository.cargo.CargoRepository;
import prt.navitruck.back.app.service.notification.AndroidPushNotificationsService;
import prt.navitruck.back.app.service.cargo.CargoUserJoinService;
import prt.navitruck.back.app.serviceImpl.cargo.CargoServiceImpl;
import prt.navitruck.back.app.serviceImpl.user.UserServiceImpl;
import prt.navitruck.back.app.utils.Constants;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/cargo")
public class CargoController extends AbstractController<Cargo, Long> {

    @Autowired
    private CargoServiceImpl cargoService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private CargoUserJoinService cargoUserJoinService;

    @Autowired
    AndroidPushNotificationsService androidPushNotificationsService;

    private static int attempt = 0;
    private final int maxAttempts = 3;
    private final int threadSleepDelay = 15;

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

    @PostMapping("/update_status")
    public ResponseEntity updateStatus(@RequestParam long cargoId,
                                 @RequestParam long userId) {

        System.out.println("updateStatus");

        return ResponseEntity.ok(ResponseDTO.builder().success(true).build());
    }
    @PostMapping("/accept")
    public ResponseEntity accept(@RequestParam long cargoId,
                                 @RequestParam long userId,
                                 @RequestParam double fee) {

        Cargo cargo = cargoService.getCargo(cargoId);
        User user = userService.getUser(1l);
        System.out.println("Task task - "+ cargo.getDeliveryTo());

        List<String> errors = null;


        if(cargo !=null && user!=null){
            try{
                cargoUserJoinService.save(new CargoUserJoin(cargo, user));
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        }

        CargoUserJoin cargoUserJoin = null;

        try{
            cargoUserJoin = getAssignedAfterDelay(cargo);
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }


        if(cargoUserJoin !=null){ //exists and return success

            if(true) {//TODO check if taskUserJoin is for current user
                return ResponseEntity.ok(ResponseDTO.builder().success(true).content(Constants.CargoStatusObj.ASSIGNED).build());
            }else{
                return ResponseEntity.ok(ResponseDTO.builder().success(true).content(Constants.CargoStatusObj.ASSIGNED_TO_OTHER).build());
            }


        }else{ //return response needs more time
            return ResponseEntity.ok(ResponseDTO.builder().success(true).content(Constants.CargoStatusObj.NOT_ASSIGNED).build());
        }

    }

    private CargoUserJoin getAssignedAfterDelay(Cargo cargo){
        threadSleep();

        CargoUserJoin cargoUserJoin = cargoUserJoinService.getAssignedByCargo(cargo);

        if(cargoUserJoin !=null){
            return cargoUserJoin;
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


    @PostMapping("/assign")
    public ResponseEntity assign(@RequestParam long userId,
                                 @RequestParam long cargoId) {

        try{
            int ret = cargoUserJoinService.assignUserToCargo(userId, cargoId);
            System.out.println("assign ret "+ret);
        }catch (Exception e){
            e.printStackTrace();
        }

        return ResponseEntity.ok(ResponseDTO.builder().success(true).build());
    }

    @PostMapping("/assignById")
    public ResponseEntity accept(@RequestParam long id) {

        try{
            CargoUserJoin cargoUserJoin = cargoUserJoinService.getOne(id);
            cargoUserJoin.getTimestamp().setUpdated(LocalDateTime.now());

            if(cargoUserJoin !=null){
                cargoUserJoin = cargoUserJoinService.update(cargoUserJoin);
                System.out.println("assign taskUserJoin.getTask().getAddressFrom() "+ cargoUserJoin.getCargo().getPickUpAt());



            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return ResponseEntity.ok(ResponseDTO.builder().success(true).build());
    }

}
