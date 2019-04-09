package framework.mail;

import framework.dataFactory.User;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class JavaMail {
    private static Properties props = MailUtils.setProp();

    public static void send(User sender, String toEmail, String subject, String text) {
        Session session = MailUtils.setSession(props, MailUtils.authenticate(sender));
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender.getEmail()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(toEmail));
            message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);

            Store store = session.getStore("imaps");
            store.connect("imap.yandex.ru", sender.getEmail(), sender.getPassword());

            Folder folder = store.getFolder("Sent");
            folder.open(Folder.READ_WRITE);
            message.setFlag(Flags.Flag.SEEN, true);
            folder.appendMessages(new Message[]{message});

            store.close();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}