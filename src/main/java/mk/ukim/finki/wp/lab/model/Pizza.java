package mk.ukim.finki.wp.lab.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Pizzas")
public class Pizza {
    @Id
    private String name;

    private String description;
    private boolean veggie;

    @JsonIgnore
    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToMany(mappedBy = "pizzas")
    private List<Ingredient> ingredients;

    public void addIngredient(Ingredient ingredient){
        this.ingredients.add(ingredient);
        ingredient.getPizzas().add(this);
    }

    public void removeIngredient(Ingredient ingredient){
        this.ingredients.remove(ingredient);
        ingredient.getPizzas().remove(this);
    }

    @Override
    public String toString() {
        return name + description;
    }
}