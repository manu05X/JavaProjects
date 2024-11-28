package org.example.evaluations.evaluation.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Notification {
 private NotificationType notificationType;
 private String message;
 private String sender;
 private String recipient;
}
