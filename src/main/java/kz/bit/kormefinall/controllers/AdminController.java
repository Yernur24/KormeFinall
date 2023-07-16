package kz.bit.kormefinall.controllers;

import kz.bit.kormefinall.models.Product;
import kz.bit.kormefinall.models.User;
import kz.bit.kormefinall.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/admin-panel")
    public String adminPanel(Model model){
        return "admin/index";
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/categories")
    public String ctindex(Model model){
        return "admin/category";
    }





}
