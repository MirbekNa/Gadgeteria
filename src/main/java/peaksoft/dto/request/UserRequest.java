package peaksoft.dto.request;
import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
