package lt.code.academy.blog.controller;

import lt.code.academy.blog.dto.User;
import lt.code.academy.blog.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/userRegistration")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String openUserRegistrationForm(Model model) {
        model.addAttribute("user", new User());

        return "form/userRegistration";
    }
}
