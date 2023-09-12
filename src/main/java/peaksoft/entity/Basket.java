package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Data;
import peaksoft.entity.IdGen.IdGenerator;

import java.util.List;

@Data
@Entity
@Table(name = "baskets")
public class Basket extends IdGenerator {
    @ManyToMany(mappedBy = "baskets", cascade = CascadeType.ALL)
    private List<Product> products;
    @OneToOne(mappedBy = "basket", cascade = CascadeType.ALL)
    private User user;
}
