package com.epamjwd.provider.model.util.mail;

import com.epamjwd.provider.exception.UtilityException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MailSender {
    private static final Logger logger = LogManager.getLogger();
    private static final String MAIL_PROPERTY_PATH = "mail/mailsend.properties";
    private static final String MAIL_USER_NAME = "mail.user.name";
    private static final String MAIL_USER_PASSWORD = "mail.user.password";
    private static final String MAIL_SUBJECT = "Verification";
    private Properties mailProperties;
    private MimeMessage mimeMessage;
    private static MailSender instance;

    private MailSender() {
        try {
            mailProperties = new Properties();
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(MAIL_PROPERTY_PATH);
            mailProperties.load(inputStream);
        } catch (IOException e) {
            logger.error("Mail properties read error", e);
        }
    }

    public static MailSender getInstance() {
        if (instance == null) {
            instance = new MailSender();
        }
        return instance;
    }

    public void send(String receiverMail, String mailBody) {
        try {
            mimeMessage = configMimeMessage(receiverMail, mailBody);
            Transport.send(mimeMessage);
        } catch (MessagingException e) {
            logger.error("Send MimeMessage error", e);
        } catch (UtilityException e) {
            logger.error("Configuration MimeMessage error", e);
        }
    }

    private MimeMessage configMimeMessage(String receiverMail, String mailBody) throws UtilityException {
        Session mailSession = createSession();
        mailSession.setDebug(true);

        try {
            mimeMessage = new MimeMessage(mailSession);
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(receiverMail));
            mimeMessage.setSubject(MAIL_SUBJECT);
            mimeMessage.setText(mailBody);
        } catch (MessagingException e) {
            logger.error("Messaging configuration error", e);
            throw new UtilityException("Messaging configuration error", e);
        }
        return mimeMessage;
    }


    private Session createSession() {
        return Session.getInstance(mailProperties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                String senderMail = mailProperties.getProperty(MAIL_USER_NAME);
                String senderPassword = mailProperties.getProperty(MAIL_USER_PASSWORD);
                return new PasswordAuthentication(senderMail, senderPassword);
            }
        });
    }

}
