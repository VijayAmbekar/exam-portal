package tech.surfer.examserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String userName;

    // TODO: Change to char array implementation
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String profile;

    private boolean enabled = true;

    // user has many roles
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JsonIgnore
    private Set<UserRole> userRole = new HashSet();

}
