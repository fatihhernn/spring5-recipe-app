package fatihhernn.springframework.spring5recipeapp.repository;

import fatihhernn.springframework.spring5recipeapp.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category,Long> {

        Optional<Category> findByDescription(String description);
}
