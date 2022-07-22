package eezn.todolist.minitodo.domain.todo.repository;

import eezn.todolist.minitodo.domain.todo.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    Category insert(Category category);

    Optional<Category> findById(int id);

    List<Category> findAll();
}
