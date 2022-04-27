package eezn.todolist.minitodo.repository;

import eezn.todolist.minitodo.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    Category insert(Category category);

    Optional<Category> findById(Integer id);

    List<Category> findAll();
}
