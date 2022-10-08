package com.example.CSC131Project;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/demo")
public class UserController
{
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add")
    public @ResponseBody String addUser(@RequestParam String name, @RequestParam String email)
    {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        userRepository.save(user);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers()
    {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }

}
