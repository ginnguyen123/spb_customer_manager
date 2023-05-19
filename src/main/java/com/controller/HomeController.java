package com.controller;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class HomeController {

    @GetMapping({"/home","/"})
    public String showHomePage(Model model){
        return "home";
    }
    @GetMapping("/blank-page")
    public String showBlankPage(){
        return "pages-blank";
    }

    @GetMapping({"/login", "/logout"})
    public String loginPage(){
        return "pages-login";
    }

}
