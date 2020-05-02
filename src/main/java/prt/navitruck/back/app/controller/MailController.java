package prt.navitruck.back.app.controller;


import com.fasterxml.jackson.annotation.JsonAlias;
import org.json.JSONArray;
import org.springframework.web.bind.annotation.ResponseBody;
import prt.navitruck.back.app.model.dto.Test;
import prt.navitruck.back.app.model.response.ResponseDTO;
import prt.navitruck.back.app.network.HttpClient;
import prt.navitruck.back.app.service.mail.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

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

    @ResponseBody
    @RequestMapping(value = "get",method = RequestMethod.GET)
    public ResponseEntity get() {

        System.out.println("Ola");

        ArrayList<Test> arrayList = new ArrayList<>();

        arrayList.add(new Test("miro", "miro@mail.com", "123131"));



        return ResponseEntity.ok(ResponseDTO.builder().success(true).content(arrayList).build());
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