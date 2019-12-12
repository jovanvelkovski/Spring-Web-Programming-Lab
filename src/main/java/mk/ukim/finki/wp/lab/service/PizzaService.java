package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Ingredient;
import mk.ukim.finki.wp.lab.model.Pizza;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PizzaService {
    List<Pizza> listPizzas();
    Page<Pizza> getAllPizzas(int page, int size);
    void addPizza(String name, String description);
    Pizza editPizza(String oldName, Pizza pizza);
    Pizza createNewPizza(Pizza pizza) throws Exception;
    void deletePizza(String name);
    Pizza getPizza(String name);
    List<Pizza> getAllPizzasWithIngredients(int totalIngredients);
    List<Ingredient> getMutualIngredientsOfPizzas(String name1, String name2) throws Exception;
}