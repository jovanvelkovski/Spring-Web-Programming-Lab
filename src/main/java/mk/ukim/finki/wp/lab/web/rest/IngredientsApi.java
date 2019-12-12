package mk.ukim.finki.wp.lab.web.rest;


import mk.ukim.finki.wp.lab.model.Ingredient;
import mk.ukim.finki.wp.lab.model.Pizza;
import mk.ukim.finki.wp.lab.service.IngredientService;
import mk.ukim.finki.wp.lab.service.impl.IngredientServiceimpl;
import org.springframework.data.domain.Page;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/ingredients", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class IngredientsApi {
    private IngredientServiceimpl ingredientsService;


    public IngredientsApi(IngredientServiceimpl ingredientsService) {
        this.ingredientsService = ingredientsService;
    }

    @PostMapping
    public Ingredient createNewIngredient(@RequestParam("name") String name,
                                          @RequestParam("spicy")Boolean spicy,
                                          @RequestParam("amount")Float amount,
                                          @RequestParam("veggie")Boolean veggie) throws Exception {
        Ingredient resultIngredient = new Ingredient(name,spicy,amount,veggie,new ArrayList<>());
        return ingredientsService.createNewIngredient(resultIngredient);
    }

    @DeleteMapping("/{id}")
    public void deleteIngredient(@PathVariable("id") String name){
        ingredientsService.deleteIngredient(name);
    }

    @GetMapping("/{id}")
    public Ingredient getIngredient(@PathVariable("id") String name){
        return ingredientsService.getIngredient(name);
    }


    @GetMapping("/{id}/pizzas")
    public List<Pizza> getAllPizzasFromIngredient (@PathVariable("id") String name){
        return ingredientsService.getIngredient(name).getPizzas();
    }

    @PatchMapping("/{id}")
    public Ingredient editIngredient(@PathVariable("id") String oldName,
                                     @RequestParam("name") String newName,
                                     @RequestParam("spicy")Boolean spicy,
                                     @RequestParam("amount")Float amount,
                                     @RequestParam("veggie")Boolean veggie){
        Ingredient ingredient = new Ingredient(newName,spicy,amount,veggie,new ArrayList<>());
        return ingredientsService.editIngredient(oldName,ingredient);
    }

    @GetMapping
    public Page<Ingredient> getAllIngredients(){
        return ingredientsService.getAllIngredients(0,10);
    }

    @GetMapping(params = "spicy")
    public List<Ingredient> getAllSpicyIngredients(@RequestParam Boolean spicy){
        return ingredientsService.getAllSpicyIngredients();
    }
}
