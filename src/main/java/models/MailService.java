package models;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

// https://stackoverflow.com/questions/11356237/sending-mail-from-yahoo-id-to-other-email-ids-using-javamail-api
public class MailService {

    public MailService(String host, String port, String email, String username, String password) {
        String[] parameters = new String[]{host, port, email, username, password};
        for (String parameter : parameters) {
            if (parameter == null || "".equals(parameter)) {
                throw new IllegalArgumentException(MailService.class.getSimpleName());
            }
        }
        Properties properties = CreateEmailSettings(host, port);
        this.EmailSession = CreateSession(properties, username, password);
        this.Email = email;
    }

    protected final Session EmailSession;
    protected final String Email;

    private static Properties CreateEmailSettings(String host, String port) {
        Properties properties = System.getProperties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.trust", host);
        properties.put("mail.debug", "false");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        return properties;
    }

    private static Session CreateSession(Properties properties, String username, String password) {
        javax.mail.Authenticator authenticator = new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };
        Session session = Session.getInstance(properties, authenticator);
        session.setDebug(false);
        return session;
    }

    private static void SendMessage(Session session, String email, String subject, String content) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject(subject);
            message.setText(content);

            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void SendMessage(String subject, String content) {
        SendMessage(this.EmailSession, this.Email, subject, content);
    }
}
