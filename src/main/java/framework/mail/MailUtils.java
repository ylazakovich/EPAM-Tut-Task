package framework.mail;

import framework.User;
import framework.mail.JavaMail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

/**
 * Helps JavaMail to set session with mail.tut.by
 *
 * @author Yaroslav Lazakovich
 * @version 1.2
 */
public class MailUtils extends JavaMail {

    protected static Properties setProp() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.yandex.ru");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");
        return properties;
    }

    protected static Session setSession(Properties props, Authenticator auth) {
        Session session;
        return session = Session.getDefaultInstance(props, auth);
    }

    protected static Authenticator authenticate(User user) {
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user.getEmail(), user.getPassword());
            }
        };
        return auth;
    }
}
