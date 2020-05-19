package prt.navitruck.back.app.controller.notification;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import prt.navitruck.back.app.service.notification.AndroidPushNotificationsService;
import prt.navitruck.back.app.utils.Constants;


@RestController
@RequestMapping("/api/notification")
public class NotificationController {



    @Autowired
    AndroidPushNotificationsService androidPushNotificationsService;

    @RequestMapping(value = "/send", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> send() throws JSONException {

        JSONObject data = new JSONObject();

        data.put("addressFrom", "2871 Cropsey Av. AP #1, Brooklyn, NY, US 11214");
        data.put("addressTo", "6421 George Washington Av. 1/3, New York, Manhattan, US 45123");

        data.put("loadTime", "4/11/2020 16:00");
        data.put("unloadTime", "6/11/2020 10:00");

        data.put("price", "925");

        CompletableFuture<String> firebaseResponse = androidPushNotificationsService.sendNotification(data);

        try {
            String firebaseResponseStr = firebaseResponse.get();

            if(firebaseResponse!=null)
                return new ResponseEntity<>(firebaseResponseStr, HttpStatus.OK);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }



        return new ResponseEntity<>("Push Notification ERROR!", HttpStatus.BAD_REQUEST);
    }


}