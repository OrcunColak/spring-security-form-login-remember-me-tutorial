package com.colak.springtutorial.controllers;

import com.colak.springtutorial.entity.Roles;
import com.colak.springtutorial.services.RoleService;
import com.colak.springtutorial.services.UserRegistrationService;
import com.colak.springtutorial.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import java.util.List;

@Controller
@Slf4j
public class RegistrationController {

    @Autowired private UserRegistrationService userRegistrationService;
    @Autowired private RoleService roleService;

    @PostMapping("/register")
    public String registerNewUser(@ModelAttribute("user") User user, Model model){
        log.info("USER {}",user.toString());
        userRegistrationService.createUser(user);
        model.addAttribute("username",user.getUsername());
        return "registerSuccess";
    }

    // http://localhost:8080/register
    @GetMapping("/register")
    public String getRegistrationPage(Model model){
        List<String> roles = roleService.getAllRoles().stream().map(Roles::getRole).toList();
        model.addAttribute("user", new User());
        model.addAttribute("dummyRoles", roles);
        return "register";
    }

    // http://localhost:8080/fail
    @GetMapping("/fail")
    public String failedLoginAttempt(){
        return "fail";
    }

    @GetMapping("/")
    public String login(){
        return "login";
    }

    @GetMapping("/home")
    @PreAuthorize("hasAuthority('ROLE_TESTER')")
    public String home(){
        return "home";
    }

    @PostMapping("/logout")
    public String performLogout(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request,response,authentication);
        return "redirect:/login";
    }

}
