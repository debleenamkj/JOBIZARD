package com.stackroute.service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;


@Service
@Slf4j
public class MailServiceImpl implements MailService {
// @Value("${sender.Email}")
//     String senderEmail;
//   @Value("${sender.Password}")
//  String senderPassword;


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
//                System.out.println(senderEmail);
                return new PasswordAuthentication(senderEmail,senderPassword);
            }
        });

        String Number = null;

        try{
            MimeMessage msg=new MimeMessage(session);
            MimeMessageHelper helper=new MimeMessageHelper(msg,true);
            helper.setFrom(new InternetAddress(senderEmail));
            msg.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(to)));
            helper.setSubject(""+companyName+" Interested in you profile");
            MimeMultipart mimeMultipart = new MimeMultipart();
            MimeBodyPart textMime = new MimeBodyPart();
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            MimeBodyPart messageBodyPart2=new MimeBodyPart();
            String content = "Hello Team,<br>This Email is to inform that "+companyName+"  has showed interest in your profile .";
            messageBodyPart.setContent(content,"text/html; charset=utf-8");
            textMime.setText("\nPlease take appropriate actions.\n");
            String content1="<b>Regards,<br>Team Jobizard </b>";
            messageBodyPart2.setContent(content1,"text/html; charset=utf-8");
            mimeMultipart.addBodyPart(messageBodyPart);
            mimeMultipart.addBodyPart(textMime);
            mimeMultipart.addBodyPart(messageBodyPart2);
            msg.setContent(mimeMultipart);
            Transport.send(msg);
            log.debug("Email Sent Successfully");
            flag=true;
        }catch(AddressException exception){
            log.error("Email service error"+exception);
        }catch(MessagingException exception) {
            log.error("email service error "+exception);
            exception.printStackTrace();
        }
        return flag;
    }
}


