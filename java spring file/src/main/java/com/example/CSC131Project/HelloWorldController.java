package com.example.CSC131Project;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloWorldController
{

    @GetMapping("/")
    @ResponseBody
    public String HelloWorld()
    {
        String message = "Hello world, this is a java spring application!!!";
        return message;
    }

    @GetMapping("/error")
    @ResponseBody
    public String Error()
    {
        return "ERROR!!! - something is wrong!!";
    }
}
