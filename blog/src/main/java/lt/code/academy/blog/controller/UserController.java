package lt.code.academy.blog.controller;

import lt.code.academy.blog.dto.User;
import lt.code.academy.blog.service.MessageService;
import lt.code.academy.blog.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/userRegistration")
public class UserController {
    private final UserService userService;
    private final MessageService messageService;

    public UserController(UserService userService, MessageService messageService) {
        this.userService = userService;
        this.messageService = messageService;
    }

    @GetMapping
    public String openUserRegistrationForm(Model model, String messageKey) {
        model.addAttribute("user", new User());
        model.addAttribute("message", messageService.getMessage(messageKey));

        return "form/userRegistration";
    }

    @PostMapping
    public String createUser(User user) {
        String messageKey ="lt.code.academy.blog.form.user.registration.succes.message";
        userService.createUser(user);

        return "redirect:/userRegistration?messageKey=" + messageKey;
    }
}
