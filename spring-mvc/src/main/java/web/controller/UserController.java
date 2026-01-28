package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserService;


import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String findAllUsers(Model model) {
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/new")
    public String newUsersForm (Model model) {
        model.addAttribute("user", new User());
        return "userForm";
    }

    @PostMapping("/new")
    public String saveUser(
            @RequestParam("firstName") String firstname,
            @RequestParam("lastName") String lastname,
            @RequestParam("email") String email) {
        User user = new User(firstname, lastname, email);
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/edit")
    public String editUsersForm (@RequestParam("id") Long id, Model model) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        return "userForm";
    }

    @PostMapping("/edit")
    public String updateUserFromEdit(
            @RequestParam("id") Long id,
            @RequestParam("firstName") String firstname,
            @RequestParam("lastName") String lastname,
            @RequestParam("email") String email){
        User existingUser = userService.findUserById(id);
        if(existingUser != null){
            existingUser.setFirstName(firstname);
            existingUser.setLastName(lastname);
            existingUser.setEmail(email);
            userService.updateUser(existingUser);
        }
        return "redirect:/users";
    }

    @PostMapping("/update")
    public String updateUser(
            @RequestParam("id") Long id,
            @RequestParam("firstName") String firstname,
            @RequestParam("lastName") String lastname,
            @RequestParam("email") String email) {
       User existingUser = userService.findUserById(id);
       if (existingUser == null) {
           existingUser.setFirstName(firstname);
           existingUser.setLastName(lastname);
           existingUser.setEmail(email);
           userService.saveUser(existingUser);
       }
        return "redirect:/users";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id")  Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
