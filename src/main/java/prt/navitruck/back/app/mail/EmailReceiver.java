package prt.navitruck.back.app.mail;

import com.sun.mail.imap.IdleManager;
import org.apache.juli.logging.Log;

import javax.annotation.Resource;
import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.event.MessageCountAdapter;
import javax.mail.event.MessageCountEvent;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailReceiver {

    ExecutorService es = Executors.newCachedThreadPool();


    public static void setMailReceiveListener(String protocol, String host, String port,
                                              String userName, String password){
        Properties properties = getServerProperties(protocol, host, port);
        Session session = Session.getDefaultInstance(properties);

        ExecutorService es = Executors.newCachedThreadPool();

//		Properties props = session.getProperties();
//		props.put("mail.event.scope", "session"); // or "application"
//		props.put("mail.event.executor", es);

        try {
            // connects to the message store
            Store store = session.getStore(protocol);
            store.connect(userName, password);

            final IdleManager idleManager = new IdleManager(session, es);


            // opens the inbox folder
            Folder folderInbox = store.getFolder("INBOX");
            folderInbox.open(Folder.READ_ONLY);

            System.out.println("Setting Listener");
            folderInbox.addMessageCountListener(new MessageCountAdapter() {
                public void messagesAdded(MessageCountEvent ev) {
                    System.out.println("messagesAdded");

                    Message[] msgs = ev.getMessages();
                    System.out.println("Folder: " + folderInbox +
                            " got " + msgs.length + " new messages");
                    readMail(msgs);
                    try {
                        // process new messages
                        idleManager.watch(folderInbox); // keep watching for new messages
                    } catch (MessagingException mex) {
                        // handle exception related to the Folder
                    }
                }
            });
            idleManager.watch(folderInbox);

        }catch (NoSuchProviderException ex) {
            System.out.println("No provider for protocol: " + protocol);
            ex.printStackTrace();
        }catch (MessagingException ex) {
            System.out.println("Could not connect to the message store");
            ex.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void readMail( Message[] messages){
        try{
            for (int i = 0; i < messages.length; i++) {
                Message msg = messages[i];
                Address[] fromAddress = msg.getFrom();
                String from = fromAddress[0].toString();
                String subject = msg.getSubject();
                String toList = parseAddresses(msg
                        .getRecipients(RecipientType.TO));
                String ccList = parseAddresses(msg
                        .getRecipients(RecipientType.CC));
                String sentDate = msg.getSentDate().toString();

                String contentType = msg.getContentType();
                String messageContent = "";

                Object o = msg.getContent();
                if (o instanceof Multipart) {
                    System.out.println("**This is a Multipart Message.  ");
                    Multipart mp = (Multipart)o;
                    System.out.println("The Multipart message has " + mp.getCount() + " parts.");
                    for (int j = 0; j < mp.getCount(); j++) {
                        BodyPart b = mp.getBodyPart(j);

                        if (b.getContentType().toLowerCase(Locale.ENGLISH).contains("multipart")) {
                            System.out.println("This content contains multipart");
                            mp = (Multipart)b.getContent();
                            j = 0;
                            continue;
                        }
                        System.out.println("This content type is " + b.getContentType());
                        if(!b.getContentType().toLowerCase(Locale.ENGLISH).contains("text/html")) {
                            continue;
                        }
                        Object o2 = b.getContent();
                        if (o2 instanceof String) {
                            System.out.println("Yuhuuu here is the string");
                            HtmlParser htmlParser = new HtmlParser(o2.toString());
                            htmlParser.parseHtml();
                        }

                    }

                }



                if (contentType.contains("text/plain")
                        || contentType.contains("text/html")) {
                    try {
                        Object content = msg.getContent();
                        if (content != null) {
                            messageContent = content.toString();
                        }
                    } catch (Exception ex) {
                        messageContent = "[Error downloading content]";
                        ex.printStackTrace();
                    }
                }

                // print out details of each message
                System.out.println("Message #" + (i + 1) + ":");
                System.out.println("\t From: " + from);
                System.out.println("\t To: " + toList);
                System.out.println("\t CC: " + ccList);
                System.out.println("\t Subject: " + subject);
                System.out.println("\t Sent Date: " + sentDate);
                System.out.println("\t Message: " + messageContent);



            }
        }catch (MessagingException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    private static Properties getServerProperties(String protocol, String host,
                                                  String port) {
        Properties properties = new Properties();

        // server setting
        properties.put(String.format("mail.%s.host", protocol), host);
        properties.put(String.format("mail.%s.port", protocol), port);

        // SSL setting
        properties.setProperty(
                String.format("mail.%s.socketFactory.class", protocol),
                "javax.net.ssl.SSLSocketFactory");
        properties.setProperty(
                String.format("mail.%s.socketFactory.fallback", protocol),
                "false");
        properties.setProperty(
                String.format("mail.%s.socketFactory.port", protocol),
                String.valueOf(port));
        properties.setProperty(
                String.format("mail.%s.usesocketchannels", protocol),
                "true");


        return properties;
    }


    private static String parseAddresses(Address[] address) {
        String listAddress = "";

        if (address != null) {
            for (int i = 0; i < address.length; i++) {
                listAddress += address[i].toString() + ", ";
            }
        }
        if (listAddress.length() > 1) {
            listAddress = listAddress.substring(0, listAddress.length() - 2);
        }

        return listAddress;
    }

}
