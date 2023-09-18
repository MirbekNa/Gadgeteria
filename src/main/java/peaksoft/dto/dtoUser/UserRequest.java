package peaksoft.dto.dtoUser;

import lombok.Builder;

@Builder
public record UserRequest(String firstName,
                          String lastName,
                          String email,
                          String password) {
    public UserRequest {
    }
}
