package prt.navitruck.back.app.mail;

import com.zaxxer.hikari.util.SuspendResumeLock;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.util.List;

public class HtmlParser implements Runnable{

    private static String HTMLSTring;
    private static short startIndex = 6;
    private static short endIndex = 14;

    public HtmlParser(String HTMLSTring){
        this.HTMLSTring = HTMLSTring;
    }

    public void run() {
        Document html = Jsoup.parse(HTMLSTring);
        Elements td = html.select("td");
        Element currentTd = td.get(2).child(0);
        List<TextNode> list = currentTd.textNodes();

        JSONObject jsonObject = new JSONObject();

        for(int i = startIndex; i< endIndex; i++){
            System.out.println(list.get(i)); 
            populateJson(list.get(i).text(), jsonObject);
        }

        System.out.println("Json length = "+jsonObject.length());
        System.out.println("Json = "+jsonObject);
    }

    private void populateJson(String txt, JSONObject jsonObject){
        int length = txt.length();
        int halfL = txt.length()/2;
        int index = 0;
        //begin iterations from middle of the string and looking for : to crate value for jsonObj
        for(int j =length/2; j<length; j++){
            if(txt.charAt(j)==':'){
                index = j;
                break;
            }else if (txt.charAt(halfL)==':'){
                index = halfL;
                break;
            }
            halfL--;
        }

        String key = null,val = null;
        if(index>0){
            key = txt.substring(0,index);
            val = txt.substring(index+2,length);

            if(key!=null && val!=null){
                jsonObject.put(key, val);
            }
        }
    }

}
