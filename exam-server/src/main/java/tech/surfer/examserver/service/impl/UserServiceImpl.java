package tech.surfer.examserver.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tech.surfer.examserver.entity.User;
import tech.surfer.examserver.entity.UserRole;
import tech.surfer.examserver.exception.UserFoundException;
import tech.surfer.examserver.repo.RoleRepository;
import tech.surfer.examserver.repo.UserRepository;
import tech.surfer.examserver.service.UserService;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    @NonNull
    private UserRepository userRepository;

    @NonNull
    private RoleRepository roleRepository;

    /**
     * Creating User
     * @param user
     * @param userRoles
     * @return
     */
    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws UserFoundException {

        User existingUser = this.userRepository.findByUserName(user.getUsername());

        if(!Objects.isNull(existingUser)) {
            log.error("User already exist");
            throw new UserFoundException("User already exist");
        } else {
            // save roles first
            roleRepository.saveAll(userRoles.stream().map(ur -> ur.getRole()).collect(Collectors.toSet()));

            user.getUserRole().addAll(userRoles);
            this.userRepository.save(user);
            return user;
        }
    }

    /**
     * Fetch user using username
     * @param userName
     * @return
     */
    @Override
    public User getUser(String userName) {
        return this.userRepository.findByUserName(userName);
    }

    @Override
    public void deleteUser(Long userId) {
        this.userRepository.deleteById(userId);
    }

    @Override
    public User updateUser(User user) {
        // Fetch user with this id from DB
        if(Objects.isNull(user) || Objects.isNull(user.getId())) {
            throw new RuntimeException("User/UserId  not found");
        }

        Optional<User> existingUser = this.userRepository.findById(user.getId());

        if(existingUser.isEmpty()) {
            throw new RuntimeException("User with given Id not found");
        }

        User updatedUser = existingUser.get();

        updatedUser.setUserName(user.getUsername());
        updatedUser.setPassword(user.getPassword());
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setPhone(user.getPhone());
        updatedUser.setProfile(user.getProfile());
        updatedUser.setEnabled(user.isEnabled());

        this.userRepository.save(updatedUser);

        return updatedUser;
    }
}
