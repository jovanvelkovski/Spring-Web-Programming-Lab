package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Pizza;

import java.util.List;

public interface PizzaService {
    List<Pizza> listPizzas();
    void addPizza(String name, String description);
}