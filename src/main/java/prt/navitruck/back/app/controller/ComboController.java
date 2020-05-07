package prt.navitruck.back.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import prt.navitruck.back.app.model.dto.ComboDTO;
import prt.navitruck.back.app.model.entity.truck.TruckParams;
import prt.navitruck.back.app.model.response.ResponseDTO;
import prt.navitruck.back.app.service.ComboService;
import prt.navitruck.back.app.service.truck.TruckParametersService;
import prt.navitruck.back.app.utils.Constants;

import java.util.List;

@RestController
@RequestMapping("/combo")
public class ComboController {

    @Autowired
    ComboService comboService;

    @Autowired
    TruckParametersService truckParametersService;

    @RequestMapping(value = "/getTruckParams", method = RequestMethod.GET)
    public ResponseEntity getTruckParams() {
        try{
            List<TruckParams> list = truckParametersService.getAll();
            return ResponseEntity.ok(ResponseDTO.builder().success(true).content(list).build());
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok(ResponseDTO.builder().success(false).build());

    }

    @RequestMapping(value = "/getMake", method = RequestMethod.GET)
    public ResponseEntity getMake() {
        System.out.println("getMake");
        try{
            List<ComboDTO> list = comboService.getComboByKey("car_make");
            return ResponseEntity.ok(ResponseDTO.builder().success(true).content(list).build());
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok(ResponseDTO.builder().success(false).build());

    }
}
