package peaksoft.dto.response;

import lombok.*;
import peaksoft.enums.Role;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class UserResponse {
  private String token;
    private String email;


}
