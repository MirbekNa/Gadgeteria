package peaksoft.dto.dtoAuthentication;

import lombok.Builder;
import peaksoft.enums.Role;
@Builder
public record AuthenticationResponse(String token, String email, Role role) {
    public AuthenticationResponse {
    }
}
