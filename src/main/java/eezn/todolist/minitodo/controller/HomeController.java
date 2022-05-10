package eezn.todolist.minitodo.controller;

import eezn.todolist.minitodo.controller.dto.UserDto;
import eezn.todolist.minitodo.domain.User;
import eezn.todolist.minitodo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("userList", userService.findAll());
        model.addAttribute("userForm", new UserDto());
        return "home";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String join(@ModelAttribute("userForm") UserDto userForm) {

        User user = new User();
        user.setUsername(userForm.getUsername());
        user.setPassword(userForm.getPassword());
        user.setEmail(userForm.getEmail());

        Integer id = 0;
        try { id = userService.join(user).getId(); }
        catch (Exception e) { System.out.println(e.getMessage()); }

        String ret = "redirect:/";
        if (id != 0) ret = "redirect:/user/" + id;
        return ret;
    }
}
