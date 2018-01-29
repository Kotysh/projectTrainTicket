package ru.dmitriykotyshov.mail;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by Дмитрий on 28.01.2018.
 */
public class Message {

    private String mail;
    private String header;
    private String text;

    public Message(String mail, String header, String text) {
        this.mail = mail;
        this.header = header;
        this.text = text;
    }

    public void sendMessage() {

        Properties p = new Properties();

        p.put("mail.smtp.host", "smtp.mail.ru");
        p.put("mail.smtp.socketFactory.port", 465);
        p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        p.put("mail.smtp.auth", "true");
        p.put("mail.smtp.port", 465);


        Session s = Session.getInstance(p,

                new javax.mail.Authenticator() {


                    protected PasswordAuthentication getPasswordAuthentication() {

                        return new PasswordAuthentication("kotyshex2k17@mail.ru", "123456ab");

                    }


                }
        );

        javax.mail.Message message = new MimeMessage(s);
        try {
            message.setFrom(new InternetAddress("kotyshex2k17@mail.ru"));
            message.setRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(mail));
            message.setSubject(header);
            message.setText(text);

            Transport.send(message);

            System.out.println("Письмо успешно отправлено");

        } catch (MessagingException e) {

            System.out.println("Письмо не отправлено: "+e.getMessage());
        }

        System.out.println(mail + " " + header + " " + text);

    }

}
