package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.mail.*;
import java.util.Properties;

/**
 *
 * Created by tgranbacka on 15-05-10.
 */
@Service
public class EmailProcessingServiceImpl implements MailService {

    private Logger logger = LoggerFactory.getLogger(EmailProcessingServiceImpl.class);


    @Override
    public void process() throws Exception {
        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "imaps");
        try {
            Session session = Session.getInstance(props, null);
            Store store = session.getStore();
            store.connect("jada.com", "jada@jada.se", "jadapwd");
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);
            Message msg = inbox.getMessage(inbox.getMessageCount());
            Address[] in = msg.getFrom();
            for (Address address : in) {
                System.out.println("FROM:" + address.toString());
            }
            Multipart mp = (Multipart) msg.getContent();
            BodyPart bp = mp.getBodyPart(0);
            System.out.println("SENT DATE:" + msg.getSentDate());
            System.out.println("SUBJECT:" + msg.getSubject());
            System.out.println("CONTENT:" + bp.getContent());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
