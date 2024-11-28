package org.example.evaluations.evaluation.configurations;

import org.example.evaluations.evaluation.models.Content;
import org.example.evaluations.evaluation.models.ContentType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class NotificationConfig {

    @Bean
    @Primary
    public Content getTextContentForTextMessage() {
        Content content = new Content();
        content.setContentType(ContentType.TEXT);
        content.setMessage("text message");
        return content;
    }

    @Bean
    public Content getXmlContentForXmlMessage() {
        Content content = new Content();
        content.setContentType(ContentType.XML);
        content.setMessage("xml message");
        return content;
    }

    @Bean
    public Content getJsonContentForJsonMessage() {
        Content content = new Content();
        content.setContentType(ContentType.JSON);
        content.setMessage("json message");
        return content;
    }

    @Bean
    public Content getHtmlContentForHtmlMessage() {
        Content content = new Content();
        content.setContentType(ContentType.HTML);
        content.setMessage("html message");
        return content;
    }
}
