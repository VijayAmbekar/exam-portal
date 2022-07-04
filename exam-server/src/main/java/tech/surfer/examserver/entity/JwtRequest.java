package tech.surfer.examserver.entity;


import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Jwt Request is mapped with this class
 */
@RequiredArgsConstructor
@Getter
@Setter
public class JwtRequest {
    @NonNull
    private String userName;
    @NonNull
    private String password;
}
