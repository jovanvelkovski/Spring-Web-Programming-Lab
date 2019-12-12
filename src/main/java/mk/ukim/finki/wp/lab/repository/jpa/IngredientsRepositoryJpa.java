package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientsRepositoryJpa extends JpaRepository<Ingredient, String> {
}
