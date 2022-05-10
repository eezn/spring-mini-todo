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
    public String read(@PathVariable("id") int userId, Model model) {

        try {
            model.addAttribute("userId", userId);
            model.addAttribute("userName", userService.findUser(userId).getUsername());
            model.addAttribute("todoList", todoService.findByUserId(userId));
            model.addAttribute("todoForm", new TodoDto());
            return "user";
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String create(@PathVariable("id") int userId,
                         @ModelAttribute("todoForm") TodoDto todoForm) {

        try { userService.findUser(userId); }
        catch (IllegalStateException e) {
            System.out.println(e.getMessage());
            return "redirect:/";
        }

        try {
            Todo todo = new Todo();
            todo.setUserId(userId);
            todo.setContent(todoForm.getContent());
            todo.setCategoryId(todoForm.getCategoryId());
            todo.setPriorityId(todoForm.getPriorityId());
            todoService.create(todo);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/user/" + userId;
    }

    @RequestMapping(value = "/{id}/check", method = RequestMethod.POST)
    public String check(@PathVariable("id") int userId,
                        @RequestParam("todo") int todoId) {
        try { todoService.toggleStatus(todoId); }
        catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/user/" + userId;
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    public String delete(@PathVariable("id") int userId,
                         @RequestParam("todo") int todoId) {
        todoService.deactivate(todoId);
        return "redirect:/user/" + userId;
    }

    @RequestMapping(value = "/{id}/modify", method = RequestMethod.POST)
    public String modify(@PathVariable("id") int userId,
                         @RequestParam("todo") int todoId, Model model) {

        try {
            model.addAttribute("userId", userId);
            model.addAttribute("userName", userService.findUser(userId).getUsername());
            model.addAttribute("target", todoService.findById(todoId));
            return "modify";
        }
        catch (IllegalStateException e) {
            System.out.println(e.getMessage());
            return "redirect:/user/" + userId;
        }
    }

    @RequestMapping(value = "/{id}/complete", method = RequestMethod.POST)
    public String complete(@PathVariable("id") int userId,
                           @RequestParam("todo") int todoId,
                           @ModelAttribute("target") TodoDto target) {

        Todo modified = todoService.findById(todoId);
        System.out.println(modified);

        modified.setContent(target.getContent());
        modified.setCategoryId(target.getCategoryId());
        modified.setPriorityId(target.getPriorityId());
        todoService.update(modified);

        return "redirect:/user/" + userId;
    }

//    @RequestMapping(value = "/{id}/complete", method = RequestMethod.GET)
//    public String redirect(@PathVariable("id") int userId) {
//        return "redirect:/user/" + userId;
//    }

}
