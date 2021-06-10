package hospital.hospital.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class SecurityService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Environment env;

    @Autowired
    private TemplateEngine templateEngine;


    public String build(String username, Long attempts) {
        Context context = new Context();
        context.setVariable("username", username);
        context.setVariable("attempts", attempts);

        return templateEngine.process("mailTemplate", context);
    }

    @Async
    public void notifyAdmin(String username, Long attempts) throws MailException {

        System.out.println("ovdee");
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(env.getProperty("spring.mail.username"));
            messageHelper.setTo("laketic.milena98@gmail.com");
            messageHelper.setSubject("Security risk");
            String content = build(username, attempts);
            System.out.println(content);
            messageHelper.setText(content, true);
        };
        try {
            javaMailSender.send(messagePreparator);
        } catch (MailException e) {
            e.printStackTrace();
        }
    }
}
