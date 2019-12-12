
package mk.ukim.finki.wp.lab.web.rest;

import mk.ukim.finki.wp.lab.model.Ingredient;
import mk.ukim.finki.wp.lab.model.Pizza;
import mk.ukim.finki.wp.lab.service.impl.PizzaServiceimpl;
import org.springframework.data.domain.Page;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/pizzas",produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class PizzasApi {

    PizzaServiceimpl pizzaService;

    public PizzasApi(PizzaServiceimpl pizzaService){
        this.pizzaService=pizzaService;
    }

    @PostMapping
    public Pizza createNewPizza(@RequestParam("name") String name,
                                @RequestParam("description")String description,
                                @RequestParam(defaultValue = "false")Boolean veggie) throws Exception {
        Pizza pizza = new Pizza(name,description,veggie,new ArrayList<>());
        return pizzaService.createNewPizza(pizza);
    }

    @PutMapping("/{id}")
    public Pizza editPizza(@PathVariable("id") String oldName,
                           @RequestParam("name") String newName,
                           @RequestParam("description")String description,
                           @RequestParam("veggie")Boolean veggie){
        Pizza newPizza = new Pizza(newName,description,veggie,new ArrayList<>());
        return pizzaService.editPizza(oldName,newPizza);
    }

    @DeleteMapping("/{id}")
    public void deletePizza(@PathVariable("id") String name){
        pizzaService.deletePizza(name);
    }

    @GetMapping("/{id}")
    public Pizza getPizza(@PathVariable("id") String name){
        return pizzaService.getPizza(name);
    }


    @GetMapping
    public Page<Pizza> getAllIngredients(){
        return pizzaService.getAllPizzas(0,10);
    }


    @GetMapping("/totalIngredients")
    public List<Pizza> getAllPizzasWithIngredients(@RequestParam("totalIngredients") int totalIngredients){
        return pizzaService.getAllPizzasWithIngredients(totalIngredients);
    }


    @GetMapping("/compare")
    public List<Ingredient> getMutualIngredientsOfPizzas(@RequestParam("pizza1") String name1,
                                                         @RequestParam("pizza2") String name2) throws Exception {
        return pizzaService.getMutualIngredientsOfPizzas(name1, name2);
    }

}