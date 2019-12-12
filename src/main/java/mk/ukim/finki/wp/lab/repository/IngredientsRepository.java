package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Ingredient;
import mk.ukim.finki.wp.lab.repository.jpa.IngredientsRepositoryJpa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class IngredientsRepository {

    IngredientsRepositoryJpa ingredientsRepositoryJPA;

    public IngredientsRepository(IngredientsRepositoryJpa ingredientsRepositoryJPA){
        this.ingredientsRepositoryJPA=ingredientsRepositoryJPA;
    }


    public Ingredient createNewIngredient (Ingredient ingredient) throws Exception {
        if(ingredientsRepositoryJPA.findById(ingredient.getName()).isPresent()){
            throw new Exception("Sostojkata so takvo ime vekje postoi!");
        }
        else if(getAllSpicyIngredients().size() >= 3 && ingredient.isSpicy()){
            throw new Exception("Ne e dozvoleno poveke od 3 spicy ingredients");
        }
        else{
            return ingredientsRepositoryJPA.save(ingredient);
        }
    }

    public void deleteIngredient(String name){
        ingredientsRepositoryJPA.deleteById(name);
    }

    public Ingredient getIngredient(String name){
        return ingredientsRepositoryJPA.findById(name).orElseThrow(RuntimeException::new);
    }

    public Ingredient editIngredient(String oldName,Ingredient newIngredient){
        if(newIngredient.getName().equals(oldName)){
            this.deleteIngredient(oldName);
        }

        return ingredientsRepositoryJPA.save(newIngredient);
    }

    public Page<Ingredient> getAllIngredients(int page, int size) {

        return ingredientsRepositoryJPA.findAll(PageRequest.of(page, size, Sort.by("name")));
    }

    public List<Ingredient> getAllSpicyIngredients(){
        List<Ingredient> lista = ingredientsRepositoryJPA.findAll();
        return lista.stream().filter(Ingredient::isSpicy).collect(Collectors.toList());
    }
}
