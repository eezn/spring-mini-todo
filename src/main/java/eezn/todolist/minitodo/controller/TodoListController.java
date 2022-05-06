package eezn.todolist.minitodo.controller;

import eezn.todolist.minitodo.domain.Todo;
import eezn.todolist.minitodo.controller.form.TodoForm;
import eezn.todolist.minitodo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class TodoListController {

    private final TodoService todoService;

    @RequestMapping(value = "/todolist", method = RequestMethod.GET)
    public String todoList(@RequestParam("id") int id, Model model) throws Exception {
        model.addAttribute("todoList", todoService.findByUserId(id));
        model.addAttribute("todoForm", new TodoForm());
        return "todolist";
    }

    @RequestMapping(value = "/todolist", method = RequestMethod.POST)
    public String create(@RequestParam("id") int id,
                         @ModelAttribute("todoForm") TodoForm todoForm) {

        Todo todo = new Todo();
        todo.setUserId(id);
        todo.setContent(todoForm.getContent());
        todo.setCategoryId(todoForm.getCategoryId());
        todo.setPriorityId(todoForm.getPriorityId());
        try {
            todoService.create(todo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/todolist/?id=" + id;
    }
}
