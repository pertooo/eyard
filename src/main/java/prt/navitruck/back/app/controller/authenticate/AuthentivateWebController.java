package prt.navitruck.back.app.controller.authenticate;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prt.navitruck.back.app.dto.LoginDto;

@RestController
@RequestMapping("/users-web")
public class AuthentivateWebController {

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/authenticate")
    @ResponseBody
    public ResponseEntity authenticate(@RequestBody LoginDto user) {

        System.out.println("authenticate username - "+user.getUsername());

        return ResponseEntity.status(1).build();
    }


    @GetMapping("/t")
    public ResponseEntity t(@RequestBody LoginDto user) {

        System.out.println("authenticate username - "+user.getUsername());

        return ResponseEntity.status(1).build();
    }

}
