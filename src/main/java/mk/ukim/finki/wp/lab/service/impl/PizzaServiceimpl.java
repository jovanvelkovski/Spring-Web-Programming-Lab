package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Ingredient;
import mk.ukim.finki.wp.lab.model.Pizza;
import mk.ukim.finki.wp.lab.repository.PizzaRepository;
import mk.ukim.finki.wp.lab.service.PizzaService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PizzaServiceimpl implements PizzaService {

    private PizzaRepository pizzaRepository;

    public PizzaServiceimpl(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @Override
    public List<Pizza> listPizzas() {
        return this.pizzaRepository.showPizzas();
    }

    @Override
    public Page<Pizza> getAllPizzas(int page, int size) {
        return this.pizzaRepository.getAllPizzas(page, size);
    }

    @Override
    public void addPizza(String name, String description) {
        if((name != null && !name.isEmpty()) && (description != null && !description.isEmpty()))
            this.pizzaRepository.showPizzas().add(new Pizza(name, description, false, new ArrayList<>()));
    }

    @Override
    public Pizza createNewPizza(Pizza pizza) throws Exception {
        return pizzaRepository.createNewPizza(pizza);
    }

    @Override
    public Pizza editPizza(String oldName,Pizza pizza) {
        return pizzaRepository.editPizza(oldName,pizza);
    }

    @Override
    public void deletePizza(String name) {
        pizzaRepository.deletePizza(name);
    }

    @Override
    public Pizza getPizza(String name) {
        return pizzaRepository.getPizza(name);
    }

    @Override
    public List<Pizza> getAllPizzasWithIngredients(int totalIngredients) {
        return pizzaRepository.getAllPizzasWithIngredients(totalIngredients);
    }

    @Override
    public List<Ingredient> getMutualIngredientsOfPizzas(String name1, String name2) throws Exception {
        return pizzaRepository.getMutualIngredientsOfPizzas(name1, name2);
    }
}