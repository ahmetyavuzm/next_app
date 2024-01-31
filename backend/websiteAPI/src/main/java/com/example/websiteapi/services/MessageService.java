package com.example.websiteapi.services;

import com.example.websiteapi.api.model.MailMessage;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;


@Service
public class MessageService {

    private static final String username = "ahmetyavuzm@gmail.com";
    private static final String password = "nyoz tsdj tnxp hmyj";

    private static final String TO_MAIL = "ahmetyavuzm@gmail.com";



    public boolean sendMessage(MailMessage mailMessage){

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(TO_MAIL)); // Alıcı e-posta adresi
            message.setSubject(mailMessage.subject);

            // HTML içeriği eklemek için MimeMultipart kullanılır
            Multipart multipart = new MimeMultipart();

            // HTML içeriği eklenir
            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(String.format("""
                            <div>
                                <h2>Gönderen : </h2>
                                <p>%s</p>
                                <h2>Mesaj:</h2>
                                <p>%s</p>
                            </div>
                            """, mailMessage.email, mailMessage.message), "text/html");

            // Parçalar Multipart'a eklenir
            multipart.addBodyPart(htmlPart);

            // Multipart, mesajın içeriği olarak ayarlanır
            message.setContent(multipart);

            Transport.send(message);

            return true;
        } catch (MessagingException e) {
            return false;
        }

    }
}
