package tech.surfer.examserver;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tech.surfer.examserver.entity.Role;
import tech.surfer.examserver.entity.User;
import tech.surfer.examserver.entity.UserRole;
import tech.surfer.examserver.repo.RoleRepository;
import tech.surfer.examserver.service.UserService;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@Slf4j
@RequiredArgsConstructor
public class ExamServerApplication implements CommandLineRunner {

	@NonNull
	private UserService userService;

	@NonNull
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(ExamServerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		log.info("Starting code");
//
//		User user = new User();
//		user.setFirstName("Vijay");
//		user.setLastName("Ambekar");
//		user.setUserName("vijay8899");
//		user.setPassword("abc");
//		user.setEmail("abc@gmail.com");
//		user.setProfile("default.png");
//
//		Role role1 = new Role();
//		role1.setRoleName("ADMIN");

//		Role role2 = new Role();
//		role2.setRoleName("BAU");
//		roleRepository.save(role2);
//
//		Set<UserRole> userRoleSet = new HashSet<>();
//		UserRole userRole = new UserRole();
//		userRole.setRole(role1);
//		userRole.setUser(user);
//		userRoleSet.add(userRole);
//
//		this.userService.createUser(user, userRoleSet);
//
//		log.info("User: Name{}, ID: {}", user.getUserName(), user.getId());
	}
}
