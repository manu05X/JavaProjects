package org.example.evaluations.evaluation.controllers;

import org.example.evaluations.evaluation.dtos.NotificationRequestDto;
import org.example.evaluations.evaluation.models.Notification;
import org.example.evaluations.evaluation.services.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    @Autowired
    private INotificationService notificationService;

    @PostMapping("/notification")
    public Notification sendNotification(@RequestBody NotificationRequestDto notificationRequest) {
       return notificationService.sendNotification(notificationRequest.getSender(),notificationRequest.getRecipient());
    }
}
