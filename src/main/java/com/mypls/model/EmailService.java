package com.mypls.model;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.*;

import java.io.IOException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.util.Properties;

public class EmailService {
    public void sendActivationEmail(User user, Long ID){

        Email from = new Email("farhanbuet09@gmail.com");
        Email to = new Email(user.getEmail()); // use your own email address here

        String subject = "Please click the lik below to activate your account.";
        Content content = new Content("text/html", "<a href=\"http://localhost:8080/activate/user/"+ID+"\">Click Here to activate.</a>\n");

        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid("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");
        Request request = new Request();

        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        try {
            request.setBody(mail.build());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Response response = null;
        try {
            response = sg.api(request);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(response.getStatusCode());
        System.out.println(response.getHeaders());
        System.out.println(response.getBody());
    }
}
