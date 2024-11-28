package org.example.evaluations.evaluation.services;

import org.example.evaluations.evaluation.models.Content;
import org.example.evaluations.evaluation.models.Notification;
import org.example.evaluations.evaluation.models.NotificationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class EmailNotificationService implements INotificationService {

    @Autowired
    private Content content;

    @Override
    public Notification sendNotification(String sender, String recipient) {
        Notification notification = new Notification();
        notification.setMessage(content.getMessage());
        notification.setSender(sender);
        notification.setRecipient(recipient);
        notification.setNotificationType(NotificationType.EMAIL);
        return notification;
    }
}
