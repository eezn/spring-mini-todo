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
@RequestMapping(value = "/todolist")
public class TodoListController {

    private final UserService userService;
    private final TodoService todoService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String todoList(@PathVariable("id") int id, Model model) throws Exception {
        try {
            userService.findUser(id);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
            return "redirect:/";
        }
        model.addAttribute("userName", userService.findUser(id).getUsername());
        model.addAttribute("todoList", todoService.findByUserId(id));
        model.addAttribute("createForm", new TodoDto());
        model.addAttribute("updateForm", new TodoDto());
        return "todolist";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String create(@PathVariable("id") int id,
                         @ModelAttribute("createForm") TodoDto createForm) {
        try {
            userService.findUser(id);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
            return "redirect:/";
        }
        Todo todo = new Todo();

        // createForm validation check
        todo.setUserId(id);
        todo.setContent(createForm.getContent());
        todo.setCategoryId(createForm.getCategoryId());
        todo.setPriorityId(createForm.getPriorityId());

        try {
            todoService.create(todo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/todolist/" + id;
    }

//    @RequestMapping(value = "/todolist", method = RequestMethod.POST)
//    public String update(@RequestParam("id") int id,
//                         @ModelAttribute("updateForm") TodoForm todoForm) {
//
//        return "redirect:/todolist/?id=" + id;
//    }


//    @RequestMapping(value = "/todolist", method = RequestMethod.POST)
//    public String deactivateItem(@RequestParam("id") int id) {
//        Todo todo = new Todo();
//        // todoId
//        todoService.deactivate(todo);
//        return "redirect:/todolist/?id=" + id;
//    }
}
