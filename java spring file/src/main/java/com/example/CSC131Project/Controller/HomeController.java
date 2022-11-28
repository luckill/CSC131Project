package com.example.CSC131Project.Controller;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController
{
    @GetMapping("/")
    public String renderHomePage()
    {
        return"index";
    }
}
