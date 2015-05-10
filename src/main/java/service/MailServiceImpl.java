package service;

import beans.MailBody;
import org.springframework.mail.MailSender;

/**
 * Created by tgranbacka on 15-05-10.
 */
public class MailServiceImpl implements MailService {

    private MailSender mailSender;

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void send(MailBody mailBody) throws Exception {

    }
}
