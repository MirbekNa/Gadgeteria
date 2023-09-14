package peaksoft.entity;

import jakarta.persistence.*;
import lombok.*;
import peaksoft.entity.IdGen.IdGenerator;
import peaksoft.enums.Role;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User extends IdGenerator {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    @OneToOne(cascade = CascadeType.ALL)
    private Basket basket;
}
