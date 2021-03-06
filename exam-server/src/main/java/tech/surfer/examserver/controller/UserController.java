package tech.surfer.examserver.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tech.surfer.examserver.entity.Role;
import tech.surfer.examserver.entity.User;
import tech.surfer.examserver.entity.UserRole;
import tech.surfer.examserver.exception.UserFoundException;
import tech.surfer.examserver.exception.UserNotFoundException;
import tech.surfer.examserver.repo.RoleRepository;
import tech.surfer.examserver.service.UserService;

import java.util.Set;

@RestController
@RequestMapping("/user")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    @NonNull
    private UserService userService;

    @NonNull
    private RoleRepository roleRepository;

    @NonNull
    private PasswordEncoder passwordEncoder;

    /**
     * creating user
     * @return
     */
    @PostMapping("/")
    public User createUser(@RequestBody User user) throws UserFoundException {

        user.setProfile("default.png");

        //encoding password with bcrypt
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // set of user Roles
        Role role = this.roleRepository.findByRoleName("BAU");

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);

        Set<UserRole> userRoleSet = Set.of(userRole);

        return this.userService.createUser(user, userRoleSet);
    }

    /**
     * Fetch User
     * @return
     */
    @GetMapping("/{userName}")
    public User getUser(@PathVariable("userName") String userName) {

        return this.userService.getUser(userName);
    }

    /**
     * delete user by Id
     */
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        this.userService.deleteUser(userId);
    }

    /**
     * Update User
     */
    @PutMapping("/")
    public User updateuser(@RequestBody User user) {
        return this.userService.updateUser(user);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity exceptionHandler(UserNotFoundException e) {
        return ResponseEntity.badRequest().body("User Not Found");
    }

    @ExceptionHandler(UserFoundException.class)
    public ResponseEntity exceptionHandlerUserFound(UserFoundException e) {
        return ResponseEntity.badRequest().body("User with this userName already exist. Try with another one!!");
    }
}
