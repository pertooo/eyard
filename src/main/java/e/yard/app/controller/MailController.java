package e.yard.app.controller;


import com.sun.xml.bind.v2.model.core.ID;
import e.yard.app.dto.ResponseDTO;
import e.yard.app.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/email")
public class MailController {

    @Autowired
    MailService mailService;

    @PostMapping("/send")
    @ResponseBody
    public ResponseEntity delete(@RequestParam String name,
                                 @RequestParam String email,
                                 @RequestParam String message,
                                 @RequestParam String submit_message) {

        System.out.println("Ola name - "+name);
        mailService.sendMail(name, email, message);

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/get")
    @ResponseBody
    public ResponseEntity get() {

        System.out.println("Ola");

        return ResponseEntity.notFound().build();
    }
}