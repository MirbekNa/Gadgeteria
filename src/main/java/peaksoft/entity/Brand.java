package peaksoft.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import peaksoft.entity.IdGen.IdGenerator;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "brands")
public class Brand extends IdGenerator {
    private String brandName;
    private String image;
}