package com.stackroute.service;
import com.stackroute.model.EmailRequest;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;


@Service
public class MailServiceImpl implements MailService {

    EmailRequest emailRequest;

    @Override
    public boolean sendEmail(String to, String subject, String message, String companyName) throws Exception {
        boolean flag = false;
        String senderEmail = "jobizard5@gmail.com";
        String senderPassword = "nwxbvzxctycldpmw";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        String Number = null;

        try{
            MimeMessage msg=new MimeMessage(session);
            MimeMessageHelper helper=new MimeMessageHelper(msg,true);
            helper.setFrom(new InternetAddress(senderEmail));
            msg.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(to)));
            helper.setSubject("User Applied for a job");
            MimeMultipart mimeMultipart = new MimeMultipart();
            MimeBodyPart textMime = new MimeBodyPart();
            MimeBodyPart messageBodyPart = new MimeBodyPart();
          //  MimeBodyPart messageBodyPart1=new MimeBodyPart();
            MimeBodyPart messageBodyPart2=new MimeBodyPart();
            String content = "Hello Team,<br>This Email is to inform that user has applied for a job .";
            messageBodyPart.setContent(content,"text/html; charset=utf-8");
            textMime.setText("\nPlease take appropriate actions.\n");
            String content1="<b>Regards,<br>Team Jobizard </b>";
            messageBodyPart2.setContent(content1,"text/html; charset=utf-8");
            mimeMultipart.addBodyPart(messageBodyPart);

            mimeMultipart.addBodyPart(textMime);
            mimeMultipart.addBodyPart(messageBodyPart2);
            msg.setContent(mimeMultipart);
            Transport.send(msg);
            System.out.println("Email sent successfully");
            flag=true;
        }catch(AddressException e){
            System.out.println("Email service error"+e);
        }catch(MessagingException e) {
            e.printStackTrace();
        }
        return flag;
    }
}


