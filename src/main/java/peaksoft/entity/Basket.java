package peaksoft.entity;

import jakarta.persistence.*;
import lombok.*;
import peaksoft.entity.IdGen.IdGenerator;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "baskets")
public class Basket extends IdGenerator {
    @ManyToMany(mappedBy = "baskets", cascade = CascadeType.ALL)
    private List<Product> products;
    @OneToOne(mappedBy = "basket", cascade = CascadeType.ALL)
    private User user;
}
