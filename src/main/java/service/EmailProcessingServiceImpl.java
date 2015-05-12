package service;

import beans.Arendetyp;
import beans.MessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.search.FlagTerm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * Created by tgranbacka on 15-05-10.
 */
@Service
public class EmailProcessingServiceImpl implements MailService {

    private Logger logger = LoggerFactory.getLogger(EmailProcessingServiceImpl.class);


    @Override
    public void process() throws Exception {
        Store store = null;
        Folder inbox = null;
        List<MessageDTO> messageDTOList = new ArrayList<>();

        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "imaps");
        try {
            Session session = Session.getInstance(props, null);
            store = session.getStore();
            store.connect("imap.jada.com", "jadauser", "jadapwd");
            inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            // Hämta alla olösta meddelanden
            Flags seen = new Flags(Flags.Flag.SEEN);
            FlagTerm unseenFlagTerm = new FlagTerm(seen, false);
            Message[] messages = inbox.search(unseenFlagTerm);
            if (messages == null || messages.length == 0) {
                logger.debug("Inga nya meddelande");
            }

            for (Message message : messages) {
                messageDTOList.add(convertToMessageDTO(message));
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (inbox != null) {
                inbox.close(true);
            }
            if (store != null) {
                store.close();
            }
        }
        logger.info("antal hämtade, olästa meddelanden: " + messageDTOList.size());
    }

    private MessageDTO convertToMessageDTO(Message message) throws MessagingException, IOException {
        MessageDTO dto = new MessageDTO();
        Address[] addresses = message.getFrom();

        // Fixa till
        dto.setArendetyp(Arendetyp.TYP1);

        if (addresses != null && addresses.length > 0) {
            // Sätt första adressen som avsändare
            dto.setAvsandare(addresses[0].toString());
        }

        dto.setDatumMottaget(message.getReceivedDate());
        dto.setDatumSkickat(message.getSentDate());
        // Lägg till TolkaÄrende med Regex
        dto.setArende(message.getSubject());
        dto.setContent(message.getContent());

        return dto;
    }
}