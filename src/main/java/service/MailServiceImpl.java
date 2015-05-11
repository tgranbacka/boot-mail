package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
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
            MimeMessage message = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo("test@test.se");
            helper.setFrom("test@test.se");
            helper.setSubject("Kexet");
            helper.setText("tjena kexet står du har och smular");

            FileSystemResource file = new FileSystemResource(new File("/Users/file"));
            helper.addAttachment("dsc1144.jpg", file);

            message.setContent(helper.getMimeMultipart());
            mailSender.send(message);
            logger.debug("Mejl skickat");
        } catch (Exception e) {
            logger.error("Wtf", e);
        }
    }
}
