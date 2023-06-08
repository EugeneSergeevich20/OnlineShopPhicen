package com.example.onlineshopphicen.controller;

import com.example.onlineshopphicen.model.Role;
import com.example.onlineshopphicen.model.User;
import com.example.onlineshopphicen.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/users")
    public String adminPage(Model model){
        model.addAttribute("users", adminService.findAllUsers());
        return "/admin/show_users";
    }

    @GetMapping("/user/{id}")
    public String showUser(@PathVariable("id") Long id, Model model){
        model.addAttribute("user", adminService.findUserById(id));

        return "/admin/show_info_user";
    }

    @GetMapping("/user/{id}/edit")
    public String editUser(Model model, @PathVariable("id") Long id){
        model.addAttribute("user", adminService.findUserById(id));
        model.addAttribute("roles", Role.values());

        return "/admin/edit_user_info";
    }

    @PostMapping("/user/{id}")
    public String updateRole(@PathVariable("id") Long id, @ModelAttribute("user") User userRole){
        User user = adminService.findUserById(id);
        user.setRole(userRole.getRole());
        adminService.updateRole(id, user);
        return "redirect:/admin/users";
    }

}
