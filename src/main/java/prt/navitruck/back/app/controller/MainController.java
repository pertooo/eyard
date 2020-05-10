package prt.navitruck.back.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prt.navitruck.back.app.controller.abstr.AbstractController;
import prt.navitruck.back.app.mail.EmailReceiver;
import prt.navitruck.back.app.model.dto.TestDTO;
import prt.navitruck.back.app.model.entity.Test;
import prt.navitruck.back.app.model.entity.TestTest;
import prt.navitruck.back.app.model.entity.truck.Truck;
import prt.navitruck.back.app.model.response.ResponseDTO;
import prt.navitruck.back.app.repository.abstr.AbstractRepository;

import java.util.List;

@RestController
@RequestMapping("/main")
public class MainController extends AbstractController<Test, Long> {

    public MainController(AbstractRepository<Test, Long> repository) {
        super(repository);
    }

    @GetMapping ("/test")
    public ResponseEntity test(    ) {
        System.out.println("save save test ");

        return ResponseEntity.ok(ResponseDTO.builder().success(true).build());
    }

    @PutMapping("/testtest")
    public ResponseEntity testtest(@RequestBody TestDTO test
                                   ) {
        System.out.println("save save test ");


        return ResponseEntity.ok(ResponseDTO.builder().success(true).build());
    }



}
