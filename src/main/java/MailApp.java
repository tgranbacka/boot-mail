import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.MailService;

@SpringBootApplication
public class MailApp {

    public static void main(String[] args) throws Exception {
        try {
            SpringApplication.run(MailApp.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ApplicationContext context =
                new ClassPathXmlApplicationContext("mailapp-spring.xml");
        MailService mailService = (MailService) context.getBean("mailService");
        mailService.work();

    }

}