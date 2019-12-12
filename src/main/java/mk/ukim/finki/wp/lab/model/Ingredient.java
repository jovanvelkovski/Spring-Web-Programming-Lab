package mk.ukim.finki.wp.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Ingredients")
public class Ingredient {
    @Id
    private String name;
    private boolean spicy;
    private float amount;
    private boolean veggie;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Pizza> pizzas;
}
