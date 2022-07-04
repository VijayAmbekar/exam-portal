package tech.surfer.examserver.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 */
@RequiredArgsConstructor
@Getter
@Setter
public class Authority implements GrantedAuthority {

    @NonNull
    private String authority;


    @Override
    public String getAuthority() {
        return this.authority;
    }
}
