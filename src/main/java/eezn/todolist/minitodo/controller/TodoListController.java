package eezn.todolist.minitodo.controller;

import eezn.todolist.minitodo.domain.Todo;
import eezn.todolist.minitodo.controller.dto.TodoDto;
import eezn.todolist.minitodo.service.TodoService;
import eezn.todolist.minitodo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/user")
public class TodoListController {

    private final UserService userService;
    private final TodoService todoService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String read(@PathVariable("id") int id, Model model) throws Exception {

        try { userService.findUser(id); }
        catch (IllegalStateException e) {
            System.out.println(e.getMessage());
            return "redirect:/";
        }

        model.addAttribute("userName", userService.findUser(id).getUsername());
        model.addAttribute("todoList", todoService.findByUserId(id));
        model.addAttribute("todoForm", new TodoDto());

        return "user";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String create(@PathVariable("id") int id,
                         @ModelAttribute("todoForm") TodoDto todoForm) {

        try { userService.findUser(id); }
        catch (IllegalStateException e) {
            System.out.println(e.getMessage());
            return "redirect:/";
        }

        Todo todo = new Todo();
        todo.setUserId(id);
        todo.setContent(todoForm.getContent());
        todo.setCategoryId(todoForm.getCategoryId());
        todo.setPriorityId(todoForm.getPriorityId());

        try { todoService.create(todo); }
        catch (Exception e) { System.out.println(e.getMessage()); }

        return "redirect:/user/" + id;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String update(@PathVariable("id") int id,
                         @ModelAttribute("todoForm") TodoDto todoForm) {

        return "redirect:/user/" + id;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") int id,
                         @RequestParam("todo") int todo) {
        todoService.deactivate(todo);
        return "redirect:/user/" + id;
    }
}
