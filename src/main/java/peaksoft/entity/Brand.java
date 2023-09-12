package peaksoft.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import peaksoft.entity.IdGen.IdGenerator;
@Data
@Entity
@Table(name = "brands")
public class Brand extends IdGenerator {
    private String brandName;
    private String image;
}