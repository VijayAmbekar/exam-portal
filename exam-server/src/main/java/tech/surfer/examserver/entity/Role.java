package tech.surfer.examserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NonNull
    private Long roleId;

    @NonNull
    private String roleName;

    // one Role belongs to many Users
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JsonIgnore
    private Set<UserRole> userRole = new HashSet();
}
