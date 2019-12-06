package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Pizza;
import mk.ukim.finki.wp.lab.repository.PizzaRepository;
import mk.ukim.finki.wp.lab.service.PizzaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaServiceimpl implements PizzaService {

    private PizzaRepository pizzaRepository;

    public PizzaServiceimpl(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @Override
    public List<Pizza> listPizzas() {
        return this.pizzaRepository.getAllPizzas();
    }

    @Override
    public void addPizza(String name, String description) {
        this.pizzaRepository.getAllPizzas().add(new Pizza(name, description));
    }
}