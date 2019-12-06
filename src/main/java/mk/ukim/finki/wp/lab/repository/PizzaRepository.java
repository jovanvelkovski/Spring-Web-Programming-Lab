
package mk.ukim.finki.wp.lab.repository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import mk.ukim.finki.wp.lab.model.Pizza;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
public class PizzaRepository {
    private List<Pizza> pizzas;

    public PizzaRepository() {
        this.pizzas = new LinkedList<>();
        pizzas.add(new Pizza("Margherita", "tomato sauce, mozzarella"));
        pizzas.add(new Pizza("Carbonara","fresh cream, mozzarella, bacon"));
        pizzas.add(new Pizza("Vegetariana","tomato sauce, mushrooms"));
        pizzas.add(new Pizza("Calzone","Pizza dough, ricotta, pepperoni, pizza sauce, olive oil"));
        pizzas.add(new Pizza("Cheddar", "Taleggio, Mascarpone, Gorgonzola, Parmesan"));
        pizzas.add(new Pizza("Capricciosa ","cheddar, tomato sauce"));
        pizzas.add(new Pizza("Burger Classic","tomato sauce, cheese, ham"));
        pizzas.add(new Pizza("Boston Barbecue","barbecue sauce, beef, mozzarella, onions"));
        pizzas.add(new Pizza("Pepperoni","ham, chicken meat, onions"));
        pizzas.add(new Pizza("Quattro Formaggi", "tomato sauce, mozzarella, sausage"));
    }

    public List<Pizza> getAllPizzas(){
        return pizzas;
    }
}