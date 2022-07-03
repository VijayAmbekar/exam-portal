package tech.surfer.examserver.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tech.surfer.examserver.entity.Role;
import tech.surfer.examserver.entity.User;
import tech.surfer.examserver.entity.UserRole;
import tech.surfer.examserver.repo.RoleRepository;
import tech.surfer.examserver.service.UserService;

import java.util.HashSet;
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

    /**
     * creating user
     * @return
     */
    @PostMapping("/")
    public User createUser(@RequestBody User user) {

        user.setProfile("default.png");
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
}
