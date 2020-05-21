package prt.navitruck.back.app.mail;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import prt.navitruck.back.app.model.entity.cargo.Cargo;
import prt.navitruck.back.app.service.cargo.CargoService;
import prt.navitruck.back.app.service.notification.AndroidPushNotificationsService;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class HtmlParserService {

    @Autowired
    AndroidPushNotificationsService androidPushNotificationsService;

    @Autowired
    CargoService cargoService;

    private static short startIndex = 6;
    private static short endIndex = 14;

    @PostConstruct
    public void init(){
        if(cargoService==null)
            System.out.println("Cargo Service is NUll");
        else
            System.out.println("Cargo Service is not NUll");
    }

    @Async
    public CompletableFuture<Boolean> populateJson(String HTMLString){
        System.out.println("New Runnable");
        Document html = Jsoup.parse(HTMLString);
        Elements td = html.select("td");
        Element currentTd = td.get(2).child(0);
        List<TextNode> list = currentTd.textNodes();

        JSONObject jsonObject = new JSONObject();

        for(int i = startIndex; i< endIndex; i++){
            System.out.println(list.get(i));
            populateJson(list.get(i).text(), jsonObject);
        }
        System.out.println(jsonObject.length());
        sendNotification(jsonObject);
        return  CompletableFuture.completedFuture(true);
    }

    @Async
    public CompletableFuture<Boolean> sendNotification(JSONObject jsonObject){

        if(jsonObject.length()>0){
            System.out.println("json Str - "+jsonObject.toString());
            //save Cargo
            Cargo cargo = new Cargo(jsonObject);
            System.out.println(cargoService);
            if(cargo!=null){
                CompletableFuture<Cargo> savedObject = cargoService.saveCargoAsync(cargo);

                //send
                if(savedObject!=null){
                    try{
                        Cargo c = savedObject.get();
                        jsonObject.put("ID",c.getId());
                        androidPushNotificationsService.sendNotification(jsonObject);

                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    System.out.println("Well Done");
                    return  CompletableFuture.completedFuture(true);
                }
            }

            return  CompletableFuture.completedFuture(false);
        }
        System.out.println("Fuck");
        return  CompletableFuture.completedFuture(false);
    }


    @Async
    void populateJson(String txt, JSONObject jsonObject){

        String pattern = "[:]+";
        String[ ] result = txt.split(pattern);

        if(result.length==2){
            jsonObject.put(result[0], result[1].substring(1));
        }
    }
}
