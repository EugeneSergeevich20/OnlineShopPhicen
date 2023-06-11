package com.example.onlineshopphicen.controller.usersControllres;

import com.example.onlineshopphicen.model.Role;
import com.example.onlineshopphicen.model.User;
import com.example.onlineshopphicen.services.usersService.AdminService;
import com.example.onlineshopphicen.services.usersService.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final UserDetailsService userDetailsService;

    @Autowired
    public AdminController(AdminService adminService, UserDetailsService userDetailsService) {
        this.adminService = adminService;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/users")
    public String adminPage(Model model){
        model.addAttribute("users", adminService.findAllUsers());
        model.addAttribute("userAuth", userDetailsService.getAuthUser());
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

    @PatchMapping("/user/{id}")
    public String updateRole(@PathVariable("id") Long id, @ModelAttribute("user") User userRole){
        User user = adminService.findUserById(id);
        user.setRole(userRole.getRole());
        adminService.updateRole(id, user);
        return "redirect:/admin/users";
    }

}
