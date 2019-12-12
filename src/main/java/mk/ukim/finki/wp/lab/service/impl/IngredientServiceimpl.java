package mk.ukim.finki.wp.lab.service.impl;


import mk.ukim.finki.wp.lab.model.Ingredient;
import mk.ukim.finki.wp.lab.repository.IngredientsRepository;
import mk.ukim.finki.wp.lab.service.IngredientService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientServiceimpl implements IngredientService {
    private IngredientsRepository ingredientsRepository;

    public IngredientServiceimpl(IngredientsRepository ingredientsRepository) {
        this.ingredientsRepository = ingredientsRepository;
    }

    @Override
    public Ingredient createNewIngredient(Ingredient ingredient) throws Exception {
        return ingredientsRepository.createNewIngredient(ingredient);
    }

    @Override
    public void deleteIngredient(String name) {
        ingredientsRepository.deleteIngredient(name);
    }

    @Override
    public Ingredient getIngredient(String name) {
        return ingredientsRepository.getIngredient(name);
    }

    @Override
    public Ingredient editIngredient(String oldname, Ingredient newIngredient) {
        return ingredientsRepository.editIngredient(oldname, newIngredient);
    }

    @Override
    public Page<Ingredient> getAllIngredients(int page, int size) {
        return ingredientsRepository.getAllIngredients(page, size);
    }

    @Override
    public List<Ingredient> getAllSpicyIngredients() {
        return ingredientsRepository.getAllSpicyIngredients();
    }
}
