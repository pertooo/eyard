package prt.navitruck.back.app.service;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class MailService {

    private static String mailTo = "pertiapertia@gmail.com";

    public void sendMail(String name, String mailFrom, String content){

        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "25");
            props.put("mail.debug", "true");
            Session session = Session.getDefaultInstance(props);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mailFrom));
            message.setRecipient(RecipientType.TO, new InternetAddress(mailTo));
            message.setSubject("Notification");
            message.setText(content, "UTF-8"); // as "text/plain"
            message.setSentDate(new Date());
            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

//    private void confirmRegistration() {
//        User user = event.getUser();
//        String token = UUID.randomUUID().toString();
//        service.createVerificationToken(user, token);
//
//        String recipientAddress = user.getEmail();
//        String confirmationUrl
//                = event.getAppUrl() + "/regitrationConfirm/?token=" + token;
//
//
//        StringBuilder msgClient = new StringBuilder();
//
//        msgClient.append("თქვენ წარმატებით დარგისტრირდით Chatsma Store ვებ გვერდზე!").append("<br>");
//        msgClient.append("ანგარაშის გასააქტიურებლად ქვევით მოცემულ ღილაკზე:").append("<br>");
//        msgClient.append("<a href=\"http://localhost:8000/front-api/auth" + confirmationUrl + "\"><b>დააჭირეთ აქ</b></a>").append("<br>");
//
//
//
//        mailSender.send(mimeMessage -> {
//            MimeMessageHelper email = new MimeMessageHelper(mimeMessage, true, "UTF-8");
//            email.setFrom(new InternetAddress("no-reply@chatsma.ge", "ჩაცმა"));
//            email.setTo(recipientAddress); // send to user email
//            email.setSubject("დაადასტურეთ მისამართი");
//            email.setText(msgClient.toString(), true);
//        });
//    }
}
