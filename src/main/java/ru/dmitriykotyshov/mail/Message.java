package ru.dmitriykotyshov.mail;

import org.apache.log4j.Logger;

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
public final class Message {

    private final static Logger logger = Logger.getLogger(Message.class);

    private final String HOST = "smtp.mail.ru";
    private final int PORT = 465;
    private final String CLASS = "javax.net.ssl.SSLSocketFactory";
    private final String AUTH = "true";

    private final String USER_NAME = "kotyshex2k17@mail.ru";
    private final String PASSWORD = "123456ab";

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

        p.put("mail.smtp.host", HOST);
        p.put("mail.smtp.socketFactory.port", PORT);
        p.put("mail.smtp.socketFactory.class", CLASS);
        p.put("mail.smtp.auth",AUTH);
        p.put("mail.smtp.port", PORT);


        Session s = Session.getInstance(p,

                new javax.mail.Authenticator() {


                    protected PasswordAuthentication getPasswordAuthentication() {

                        return new PasswordAuthentication(USER_NAME, PASSWORD);

                    }


                }
        );

        javax.mail.Message message = new MimeMessage(s);

        try {
            message.setFrom(new InternetAddress(USER_NAME));
            message.setRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(mail));
            message.setSubject(header);
            message.setText(text);

            Transport.send(message);

            logger.info("message is sent to - "+mail);

        } catch (MessagingException e) {

            logger.warn("message is not sent", e);
        }

        System.out.println(mail + " " + header + " " + text);

    }

}
