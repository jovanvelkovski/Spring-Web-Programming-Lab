package mk.ukim.finki.wp.lab.dataholder;


import lombok.Getter;
import mk.ukim.finki.wp.lab.model.Ingredient;
import mk.ukim.finki.wp.lab.model.Pizza;
import mk.ukim.finki.wp.lab.repository.jpa.IngredientsRepositoryJpa;
import mk.ukim.finki.wp.lab.repository.jpa.PizzaRepositoryJpa;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@Component
@Getter
public class DataHolder {

    private List<Pizza> pizzas;
    private List<Ingredient> ingredients;

    public final PizzaRepositoryJpa pizzaRepositoryJPA;
    public final IngredientsRepositoryJpa ingredientsRepositoryJPA;

    public DataHolder(PizzaRepositoryJpa pizzaRepositoryJPA,IngredientsRepositoryJpa ingredientsRepositoryJPA){
        pizzas = new ArrayList<Pizza>();
        ingredients = new ArrayList<Ingredient>();
        this.pizzaRepositoryJPA=pizzaRepositoryJPA;
        this.ingredientsRepositoryJPA=ingredientsRepositoryJPA;
    }

    @PostConstruct
    public void init(){
        pizzas.add(new Pizza("Margherita", "tomato sauce, mozzarella", true, new ArrayList<>()));
        pizzas.add(new Pizza("Carbonara","fresh cream, mozzarella, bacon", false, new ArrayList<>()));
        pizzas.add(new Pizza("Vegetariana","tomato sauce, mushrooms", true, new ArrayList<>()));
        pizzas.add(new Pizza("Calzone","Pizza dough, ricotta, pepperoni, pizza sauce, olive oil", false, new ArrayList<>()));
        pizzas.add(new Pizza("Cheddar", "Taleggio, Mascarpone, Gorgonzola, Parmesan", false, new ArrayList<>()));
        pizzas.add(new Pizza("Capricciosa ","cheddar, tomato sauce", false, new ArrayList<>()));
        pizzas.add(new Pizza("Burger Classic","tomato sauce, cheese, ham", false, new ArrayList<>()));
        pizzas.add(new Pizza("Boston Barbecue","barbecue sauce, beef, mozzarella, onions", false, new ArrayList<>()));
        pizzas.add(new Pizza("Pepperoni","ham, chicken meat, onions", false, new ArrayList<>()));
        pizzas.add(new Pizza("Quattro Formaggi", "tomato sauce, mozzarella, sausage", false, new ArrayList<>()));


        Ingredient ketchup = new Ingredient("Ketchup",false,10,true,new ArrayList<>());
        Ingredient origano = new Ingredient("Origano",true,12,true,new ArrayList<>());
        Ingredient afion = new Ingredient("Afion",true,6,true,new ArrayList<>());

        ingredients.add(ketchup);
        ingredients.add(origano);
        ingredients.add(afion);

        pizzas.get(0).addIngredient(ketchup);
        pizzas.get(0).addIngredient(afion);
        pizzas.get(1).addIngredient(ketchup);
        pizzas.get(2).addIngredient(ketchup);
        pizzas.get(3).addIngredient(origano);
        pizzas.get(4).addIngredient(origano);
        pizzas.get(5).addIngredient(origano);
        pizzas.get(6).addIngredient(origano);
        pizzas.get(7).addIngredient(afion);
        pizzas.get(8).addIngredient(afion);
        pizzas.get(9).addIngredient(ketchup);


        // Initial save of all objects in the database
        if(this.pizzaRepositoryJPA.count() == 0){
            this.pizzaRepositoryJPA.saveAll(pizzas);
            this.ingredientsRepositoryJPA.saveAll(ingredients);
        }
    }



    public List<Pizza> getAllPizzas(){
        return pizzas;
    }
}
