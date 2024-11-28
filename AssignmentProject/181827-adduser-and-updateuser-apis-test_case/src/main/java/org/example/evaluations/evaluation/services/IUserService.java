package org.example.evaluations.evaluation.services;

import org.example.evaluations.evaluation.models.User;

public interface IUserService {
    User add(User user);

    User update(User user,Long userId);
}
