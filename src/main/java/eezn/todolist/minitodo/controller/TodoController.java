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
public class TodoController {

    private final UserService userService;
    private final TodoService todoService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String read(@PathVariable("id") int id, Model model) {

        try {
            model.addAttribute("userId", id);
            model.addAttribute("userName", userService.findUser(id).getUsername());
            model.addAttribute("todoList", todoService.findByUserId(id));
            model.addAttribute("todoForm", new TodoDto());
            return "user";
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String create(@PathVariable("id") int id,
                         @ModelAttribute("todoForm") TodoDto todoForm) {

        try { userService.findUser(id); }
        catch (IllegalStateException e) {
            System.out.println(e.getMessage());
            return "redirect:/";
        }

        try {
            Todo todo = new Todo();
            todo.setUserId(id);
            todo.setContent(todoForm.getContent());
            todo.setCategoryId(todoForm.getCategoryId());
            todo.setPriorityId(todoForm.getPriorityId());
            todoService.create(todo);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/user/" + id;
    }

    @RequestMapping(value = "/{id}/check", method = RequestMethod.POST)
    public String check(@PathVariable("id") int id,
                        @RequestParam("todo") int todoId) {
        try { todoService.toggleStatus(todoId); }
        catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/user/" + id;
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    public String delete(@PathVariable("id") int id,
                         @RequestParam("todo") int todoId) {
        todoService.deactivate(todoId);
        return "redirect:/user/" + id;
    }

    @RequestMapping(value = "/{id}/modify", method = RequestMethod.POST)
    public String modify(@PathVariable("id") int id,
                         @RequestParam("todo") int todoId, Model model) {

        try {
            userService.findUser(id);
            model.addAttribute("userId", id);
            model.addAttribute("userName", userService.findUser(id).getUsername());
            model.addAttribute("todo", todoService.findById(todoId).getContent());
            model.addAttribute("todoForm", new TodoDto());
            return "modify";
        }
        catch (IllegalStateException e) {
            System.out.println(e.getMessage());
            return "redirect:/user/" + id;
        }
    }

}
