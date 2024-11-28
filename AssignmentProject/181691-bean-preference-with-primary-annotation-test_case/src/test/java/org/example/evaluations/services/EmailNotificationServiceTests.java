package org.example.evaluations.services;

import org.example.evaluations.evaluation.models.Content;
import org.example.evaluations.evaluation.models.Notification;
import org.example.evaluations.evaluation.models.NotificationType;
import org.example.evaluations.evaluation.services.INotificationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EmailNotificationServiceTests {

    @MockBean
    private Content content;

    @Autowired
    private INotificationService notificationService;

    @Test
    void testSendNotification() {
        // Arrange
        String expectedMessage = "text message";
        when(content.getMessage()).thenReturn(expectedMessage);
        String sender = "sender@gmail.com";
        String recipient = "recipient@gmail.com";

        // Act
        Notification notification = notificationService.sendNotification(sender, recipient);

        // Assert
        assertEquals(expectedMessage, notification.getMessage(),"Please try getting message from Content injected inside EmailNotificationService.");
        assertEquals(sender, notification.getSender(),"Name of sender inside Notification should match with sender name passed as argument to sendNotification method.");
        assertEquals(recipient, notification.getRecipient(),"Name of recipient inside Notification should match with recipient name passed as argument to sendNotification method.");
        assertEquals(NotificationType.EMAIL, notification.getNotificationType(), "Notification Type should be EMAIL in EmailNotificationService.");
    }
}
