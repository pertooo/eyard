package prt.navitruck.back.app.mail;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.util.List;

public class HtmlParser {

    private static String HTMLSTring;

    public HtmlParser(String HTMLSTring){
        this.HTMLSTring = HTMLSTring;
    }

    public static void parseHtml(){
        Document html = Jsoup.parse(HTMLSTring);
        Elements td = html.select("td");
        Element currentTd = td.get(2).child(0);
        List<TextNode> list = currentTd.textNodes();

        for(int i = 6; i< 20; i++){
            System.out.println(list.get(i));  //ეს ობიექტები მჭირდება, ვალუე უნდა ამოვიღო პახოდუ
        }

//        for(Node child : list){
//            if(child.toString().contains("Miles"))
//                System.out.println(child);
//
//        }

//        for (Node child : currentTd.childNodes()) {
//            if (child instanceof TextNode) {
//                if( ((TextNode) child).text().contains("Miles"))
//                    System.out.println(((TextNode) child).text());
//                else if(((TextNode) child).text().contains("Pieces"))
//                    System.out.println(((TextNode) child).text());
//                else if(((TextNode) child).text().contains("Weight"))
//                    System.out.println(((TextNode) child).text());
//            }
//        }
    }
}
