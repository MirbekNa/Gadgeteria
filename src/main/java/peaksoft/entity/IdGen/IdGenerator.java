package peaksoft.entity.IdGen;

import jakarta.persistence.*;
import lombok.Data;
@MappedSuperclass
@Data
public class IdGenerator {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
}
