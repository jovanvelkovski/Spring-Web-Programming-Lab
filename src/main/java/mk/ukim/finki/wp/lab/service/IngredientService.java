package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Ingredient;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IngredientService {
    Ingredient createNewIngredient(Ingredient ingredient) throws Exception;
    void deleteIngredient(String name);
    Ingredient getIngredient(String name);
    Ingredient editIngredient(String oldname, Ingredient newIngredient);
    Page<Ingredient> getAllIngredients(int page, int size);
    List<Ingredient> getAllSpicyIngredients();
}
