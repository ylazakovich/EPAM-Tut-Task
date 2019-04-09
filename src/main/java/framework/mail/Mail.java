package framework.mail;

import framework.dataFactory.User;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Mail {
    private static Properties props = MailUtils.setProp();

    public static void sendTo(User sender, String toEmail, String subject, String text) {
        Session session = MailUtils.setSession(props, MailUtils.authenticate(sender));
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender.getEmail()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}