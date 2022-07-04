package tech.surfer.examserver.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * JwtToken is passed as response using this class
 */
@RequiredArgsConstructor
@Getter
@Setter
public class JwtResponse {
    @NonNull
    private String token;
}
