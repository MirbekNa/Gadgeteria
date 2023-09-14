package peaksoft.entity;

import jakarta.persistence.*;
import lombok.*;
import peaksoft.entity.IdGen.IdGenerator;
import peaksoft.enums.Category;
import peaksoft.enums.Country;

import java.math.BigDecimal;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "products")
public class Product extends IdGenerator {
    private String name;
    private BigDecimal price;
    @ElementCollection
    private List<String> images;
    private String characteristic;
    private Boolean isFavorite;
    @Enumerated(EnumType.STRING)
    private Country madeIn;
    @Enumerated(EnumType.STRING)
    private Category category;
@OneToMany
    private List<Comment> comment;
    @ManyToOne(cascade = CascadeType.ALL)
    private Brand brand;
@ManyToMany
    private List<Basket>baskets;
}
