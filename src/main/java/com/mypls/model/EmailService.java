package com.mypls.model;

//import com.mailjet.client.ClientOptions;
//import com.mailjet.client.MailjetClient;
//import com.mailjet.client.MailjetRequest;
//import com.mailjet.client.MailjetResponse;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
//import com.mailjet.client.resource.Emailv31;
//import com.mypls.util.HibernateUtil;
import com.mypls.Application;
import com.mypls.ui.WebServer;
import com.mypls.util.ConfigUtil;
import com.mypls.util.HibernateUtil;
import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.HibernateException;
import org.json.JSONArray;
import org.json.JSONObject;

//import javax.mail.*;


//import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.*;


public class EmailService {
    Properties prop =new ConfigUtil().configuration();
    SendGrid sg = new SendGrid(prop.getProperty("mypls.email.token"));
    Email from = new Email(prop.getProperty("mypls.twilio.email"));
    public void sendActivationEmail(User user, Long ID){

        Email to = new Email(user.getEmail()); // use your own email address here

        String subject = "Please click the lik below to activate your account.";
        Content content = new Content("text/html", "<a href=\"http://localhost:8080/activate/user/"+ID+"\">Click Here to activate.</a>\n");

        Mail mail = new Mail(from, subject, to, content);

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

    public String sendForgotPasswordEmail(String email)  throws MailjetException, MailjetSocketTimeoutException {

        Session session = null;
        try{

            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM User WHERE email=:email");
            query.setParameter("email", email);

            List<User> resultList = query.list();

            if(resultList!=null){
                User user = resultList.get(0);
                Email to = new Email(email); // use your own email address here

                String subject = "Your temporary password.";
                Content content = new Content("text/html", "Your Password Is: "+user.getPassword()+"\n");

                Mail mail = new Mail(from, subject, to, content);

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
            else {
                return null;
            }


        } catch (HibernateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } finally {
            session.close();
            return "Exist";
        }
    }
}
