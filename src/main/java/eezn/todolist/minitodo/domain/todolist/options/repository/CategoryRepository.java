package eezn.todolist.minitodo.domain.todolist.options.repository;

import eezn.todolist.minitodo.domain.todolist.options.data.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    Category insert(Category category);

    Optional<Category> findById(int id);

    List<Category> findAll();
}
