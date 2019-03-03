package Services;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by Администратор
 */
public class MailService {

    private static final String ENCODING = "UTF-8";
    private static final String SMTP_HOST = "smtp.mail.ru";
    private static final String FROM = "inside_the_nine88@mail.ru";
    private static final String LOGIN = "inside_the_nine88@mail.ru";
    private static final String PASSWORD = "****";
    private static final String SMTP_PORT = "465";

    public MailService() {}

    public void sendMail(String to, String title, String content ){
        Authenticator auth = new MyAuthenticator(LOGIN , PASSWORD);

        Properties props = System.getProperties();
        props.put("mail.smtp.port", SMTP_PORT);
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.mime.charset", ENCODING);

        Session session = Session.getDefaultInstance(props, auth);

        Message msg = new MimeMessage(session);

        try {
            msg.setFrom(new InternetAddress(FROM));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            msg.setSubject(title);
            msg.setText(content);
            Transport.send(msg);
        } catch (MessagingException e) {
            System.out.println("Some problems with Mail sending");
            e.printStackTrace();
        }
    }

}

class MyAuthenticator extends Authenticator {

    private String user;
    private String password;

    MyAuthenticator(String user, String password) {
        this.user = user;
        this.password = password;
    }

    @Override
    public PasswordAuthentication getPasswordAuthentication() {
        String user = this.user;
        String password = this.password;
        return new PasswordAuthentication(user, password);
    }

}
