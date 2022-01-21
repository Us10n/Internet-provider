package com.epamjwd.provider.model.util.mail;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MailSenderTest {

    @Test
    public void testSend() {
        String message = "To activate an account on the Internet Provider resource, follow the following link: <a href='http://localhost:8080/provider/controller?command=verify&token=609abaa9f43d0e25'>verification</a>";
        String receiver="cron96@list.ru";
        MailSender.getInstance().send(receiver,message);
    }
}