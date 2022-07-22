package eezn.todolist.minitodo.domain.todo.service.comparator;

import eezn.todolist.minitodo.domain.todo.model.Todo;

import java.util.Comparator;

public class PriorityComparator implements Comparator<Todo> {

    @Override
    public int compare(Todo o1, Todo o2) {
        if (o1.getCategoryId() == o2.getCategoryId()) {
            return Integer.compare(o1.getPriorityId(), o2.getPriorityId());
        } else {
            return Integer.compare(o1.getCategoryId(), o2.getCategoryId());
        }
    }
}
