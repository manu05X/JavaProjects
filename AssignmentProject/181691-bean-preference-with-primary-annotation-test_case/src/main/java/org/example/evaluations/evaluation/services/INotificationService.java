package org.example.evaluations.evaluation.services;

import org.example.evaluations.evaluation.models.Notification;

public interface INotificationService {

    Notification sendNotification(String sender, String recipient);
}
