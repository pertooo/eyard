package prt.navitruck.back.app.controller;


import org.springframework.web.bind.annotation.ResponseBody;
import prt.navitruck.back.app.network.HttpClient;
import prt.navitruck.back.app.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/data")
    @ResponseBody
    public ResponseEntity data(@RequestParam String note,
                               @RequestParam String zip,
                               @RequestParam String status) {

        System.out.println("data note - "+note);
        System.out.println("data zip - "+zip);
        System.out.println("data status - "+status);

        HttpClient client = new HttpClient(note, zip, status);
        client.sendData();

        return ResponseEntity.notFound().build();
    }
}