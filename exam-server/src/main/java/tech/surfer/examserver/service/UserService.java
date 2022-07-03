package tech.surfer.examserver.service;

import tech.surfer.examserver.entity.User;
import tech.surfer.examserver.entity.UserRole;

import java.util.Set;

public interface UserService {

    // create user
    User createUser(User user, Set<UserRole> userRoles);

    User getUser(String userName);

    /**
     * delete user
     */
    public void deleteUser(Long userId);

    User updateUser(User user);
}
