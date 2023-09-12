package peaksoft.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import peaksoft.entity.IdGen.IdGenerator;
@Data
@Entity
@Table(name = "favorites")
public class Favorite extends IdGenerator {

    @OneToOne
    private User user;

    @OneToOne
    private Product product;


}
