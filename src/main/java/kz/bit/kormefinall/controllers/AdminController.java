package kz.bit.kormefinall.controllers;

import kz.bit.kormefinall.models.Permission;
import kz.bit.kormefinall.models.User;
import kz.bit.kormefinall.services.PermissionService;
import kz.bit.kormefinall.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;
    private final PermissionService permissionService;

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

    @PostMapping("users/{userId}/changeRole")
    public String changeUserRole(@PathVariable("userId") Long userId, @RequestParam("newRole") String newRole) {
        User user = userService.findById(userId);
        if (user != null) {
            List<Permission> permissions = user.getPermissions();
            for (Permission permission : permissions) {
                permissionService.changeRole(permission.getId(), newRole);
            }
            return "redirect:/" + userId; // Redirect to the user details page
        } else {
            return "redirect:/"; // Redirect to the user list page or an error page
        }
    }
}
