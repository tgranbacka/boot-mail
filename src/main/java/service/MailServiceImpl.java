package service;

import beans.MailBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * Created by tgranbacka on 15-05-10.
 */
@Service
public class MailServiceImpl implements MailService {

    private Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);
    private JavaMailSender mailSender;

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void worker() {

    }

    @Override
    public void work() throws Exception {

        try {
            ApplicationContext context =
                    new ClassPathXmlApplicationContext("mailapp-spring.xml");


            MimeMessage message = mailSender.createMimeMessage();

// use the true flag to indicate you need a multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo("photo@granbacka.se");

            helper.setText("Mejlar");

// let's attach the infamous windows Sample file (this time copied to c:/)
            FileSystemResource file = new FileSystemResource(new File("/Users/tgranbacka/Downloads/dsc1144.jpg"));
            helper.addAttachment("dsc1144.jpg", file);

            mailSender.send(message);
        } catch (Exception e) {
            logger.error("Wtf", e);
        }
    }
}
