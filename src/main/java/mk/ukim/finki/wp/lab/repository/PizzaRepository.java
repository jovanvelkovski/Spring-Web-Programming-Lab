package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.dataholder.DataHolder;
import mk.ukim.finki.wp.lab.model.Ingredient;
import mk.ukim.finki.wp.lab.model.Pizza;
import mk.ukim.finki.wp.lab.repository.jpa.PizzaRepositoryJpa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Repository
public class PizzaRepository {
    private DataHolder dataHolder;
    private PizzaRepositoryJpa pizzaRepositoryJpa;

    public PizzaRepository(DataHolder dataHolder, PizzaRepositoryJpa pizzaRepositoryJpa) {
        this.dataHolder = dataHolder;
        this.pizzaRepositoryJpa = pizzaRepositoryJpa;
    }

    public List<Pizza> showPizzas() {
        return dataHolder.getAllPizzas();
    }

    public Page<Pizza> getAllPizzas(int page, int size) {
        return pizzaRepositoryJpa.findAll(PageRequest.of(page, size, Sort.by("name")));
    }

    public Pizza createNewPizza(Pizza pizza) throws Exception {
        if (pizza.isVeggie())
            for (Ingredient i : pizza.getIngredients())
                if (!i.isVeggie())
                    throw new Exception("Sostojkite ne se veggie");

        return pizzaRepositoryJpa.save(pizza);
    }

    public Pizza editPizza(String oldName, Pizza newPizza){
        if(!oldName.equals(newPizza.getName())){
            pizzaRepositoryJpa.deleteById(oldName);
        }
        return pizzaRepositoryJpa.save(newPizza);
    }

    public void deletePizza(String name) {
        pizzaRepositoryJpa.deleteById(name);
    }

    public Pizza getPizza(String name) {
        return pizzaRepositoryJpa.findById(name).orElseThrow(RuntimeException::new);
    }

    public List<Pizza> getAllPizzasWithIngredients(int totalIngredients) {
        List<Pizza> pizzas = pizzaRepositoryJpa.findAll();

        List<Pizza> result = new ArrayList<Pizza>();
        for (Pizza p : pizzas)
            if (p.getIngredients().size() < totalIngredients)
                result.add(p);

        return result;
    }

    public List<Ingredient> getMutualIngredientsOfPizzas(String name1, String name2) throws Exception {
        Pizza pizza1 = pizzaRepositoryJpa.findById(name1).orElseThrow(Exception::new);
        Pizza pizza2 = pizzaRepositoryJpa.findById(name2).orElseThrow(Exception::new);

        List<Ingredient> result = new LinkedList<>();
        for (Ingredient i : pizza1.getIngredients()) {
            if (pizza2.getIngredients().contains(i))
                result.add(i);
        }
        return result;
    }
}