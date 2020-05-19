package prt.navitruck.back.app.service.notification;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import prt.navitruck.back.app.utils.Constants;

@Service
public class AndroidPushNotificationsService {

    private static final String FIREBASE_SERVER_KEY = "AAAAywMza6Q:APA91bEr2T3Nb5bDOalEOC88w2WM8xoWV8-n3A5NEGXnT4MdyW2JxUWIcSAIQyAv9C143xeg4mAjym-yp53kQCEfIB4kDfyTW1RCuglpXuqVhPsqeTEQthPNp_YbW3RPO5FYHlEUIBLI";
    private static final String FIREBASE_API_URL = "https://fcm.googleapis.com/fcm/send";

    @Async
    public CompletableFuture<String> sendNotification(JSONObject jsonObject){

        System.out.println("AndroidPushNotificationsService - Send");

        JSONObject body = createBoydForNotification(jsonObject);
        HttpEntity<String> request = new HttpEntity<>(body.toString());

        CompletableFuture<String> pushNotification = send(request);
        CompletableFuture.allOf(pushNotification).join();

        try {
            String firebaseResponse = pushNotification.get();
            return CompletableFuture.completedFuture(firebaseResponse);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject createBoydForNotification(JSONObject jsonObject){
        JSONObject body = new JSONObject();

        body.put("condition", Constants.TOPIC);
        body.put("priority", "high");
        body.put("data", jsonObject);

        return body;
    }

    @Async
    public CompletableFuture<String> send(HttpEntity<String> entity) {

        RestTemplate restTemplate = new RestTemplate();

        ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + FIREBASE_SERVER_KEY));
        interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));
        restTemplate.setInterceptors(interceptors);

        String firebaseResponse = restTemplate.postForObject(FIREBASE_API_URL, entity, String.class);

        return CompletableFuture.completedFuture(firebaseResponse);
    }
}