package prt.navitruck.back.app.network;

import com.squareup.okhttp.*;

import java.io.IOException;

public class HttpClient {

    private String token = "10DINy0IrboFxUnppEG2Ug";

    String note, zip, status = null;

    public HttpClient(String token){
        if(token!=null)
            this.token = token;
    }

    public HttpClient(String note, String zip, String status){
        this.note = note;
        this.zip = zip;
        this.status = status;
    }

    public void sendData(){
        try{
            OkHttpClient client = new OkHttpClient();

            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded"); //multipart/form-data; boundary=----WebKitFormBoundarypA5SEdmXNQ7TgTa3,application/x-www-form-urlencoded
            com.squareup.okhttp.RequestBody body = com.squareup.okhttp.RequestBody.create(mediaType, "loadStatuses=0.33.1.15.1.1.2.1&0.33.1.15.5.1="+zip+"&0.33.1.15.5.3="+note+"&0.33.1.15.5.5=Add Update&wosid="+token);
            Request request = new Request.Builder()
                    .url("http://admin.marsonllc.com/cgi-bin/WebObjects/Sprinter.woa/1/wo/"+token+"/25.0.33.1.15")
                    .method("POST", body)
                    .addHeader("Connection", "keep-alive")
                    .addHeader("Cache-Control", "max-age=0")
                    .addHeader("Origin", "http://admin.marsonllc.com")
                    .addHeader("Upgrade-Insecure-Requests", "1")
                    .addHeader("Content-Type", "application/x-www-form-urlencoded") //multipart/form-data; boundary=----WebKitFormBoundarypA5SEdmXNQ7TgTa3
                    .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36")
                    .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
                    .addHeader("Referer", "http://admin.marsonllc.com/cgi-bin/WebObjects/Sprinter.woa/1/wo/"+token+"/14.0.33.1.21.3")
                    .addHeader("Accept-Language", "en-GB,en;q=0.9,en-US;q=0.8,ka;q=0.7")
                    .addHeader("Cookie", "wosid="+token+"; woinst=1; routeid_sprinter=.sprinter_2002")
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .addHeader("Cookie", "wosid="+token+"; woinst=1; routeid_sprinter=.sprinter_2002")
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    System.out.println("fck");
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected code " + response);
                    } else {
                        System.out.println("yehee");
                    }
                }

            });


        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void sendImg(){
        try{
            OkHttpClient client = new OkHttpClient();

            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded"); //multipart/form-data; boundary=----WebKitFormBoundarypA5SEdmXNQ7TgTa3,application/x-www-form-urlencoded
            com.squareup.okhttp.RequestBody body = com.squareup.okhttp.RequestBody.create(mediaType, "loadStatuses=0.33.1.15.1.1.2.1&0.33.1.15.5.1="+zip+"&0.33.1.15.5.3="+note+"&0.33.1.15.5.5=Add Update&wosid="+token);
            Request request = new Request.Builder()
                    .url("http://admin.marsonllc.com/cgi-bin/WebObjects/Sprinter.woa/1/wo/"+token+"/25.0.33.1.15")
                    .method("POST", body)
                    .addHeader("Connection", "keep-alive")
                    .addHeader("Cache-Control", "max-age=0")
                    .addHeader("Origin", "http://admin.marsonllc.com")
                    .addHeader("Upgrade-Insecure-Requests", "1")
                    .addHeader("Content-Type", "application/x-www-form-urlencoded") //multipart/form-data; boundary=----WebKitFormBoundarypA5SEdmXNQ7TgTa3
                    .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36")
                    .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
                    .addHeader("Referer", "http://admin.marsonllc.com/cgi-bin/WebObjects/Sprinter.woa/1/wo/"+token+"/14.0.33.1.21.3")
                    .addHeader("Accept-Language", "en-GB,en;q=0.9,en-US;q=0.8,ka;q=0.7")
                    .addHeader("Cookie", "wosid="+token+"; woinst=1; routeid_sprinter=.sprinter_2002")
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .addHeader("Cookie", "wosid="+token+"; woinst=1; routeid_sprinter=.sprinter_2002")
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    System.out.println("fck");
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected code " + response);
                    } else {
                        System.out.println("yehee");
                    }
                }

            });


        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
