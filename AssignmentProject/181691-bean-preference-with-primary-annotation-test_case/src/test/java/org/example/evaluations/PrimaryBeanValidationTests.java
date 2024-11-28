package org.example.evaluations;

import org.example.evaluations.evaluation.models.Content;
import org.example.evaluations.evaluation.models.ContentType;
import org.example.evaluations.evaluation.models.Notification;
import org.example.evaluations.evaluation.models.NotificationType;
import org.example.evaluations.evaluation.services.INotificationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PrimaryBeanValidationTests {

    @Autowired
    private INotificationService notificationService;

    @Autowired
    private Content content;


    @Test
    public void testEmailNotificationServiceShouldGetResolvedWheneverINotificationServiceIsUsed() {

        //Act
        Notification notification = notificationService.sendNotification("sender","email");

        //Assert
        assertEquals(NotificationType.EMAIL,notification.getNotificationType(),"EmailNotificationService should get resolved whenever we try to achieve Dependency Injection on INotificationService and hence NotificationType will be Email.");
    }

    @Test
    public void testContentBeanCreation() {
        //Assert
        assertEquals(ContentType.TEXT,content.getContentType(),"Default Content Bean should always be of Content Type - TEXT. You should make that Bean Primary in Configuration File.");
    }
}
