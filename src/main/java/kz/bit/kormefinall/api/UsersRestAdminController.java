package kz.bit.kormefinall.api;

import kz.bit.kormefinall.dto.UserDTO;
import kz.bit.kormefinall.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/users")
public class UsersRestAdminController {

    private final UserService userService;

    @PostMapping(value = "/ban")
    public ResponseEntity<?> banUser(@RequestParam(name = "user_id") Long id,
                                     @RequestParam(name = "banned") boolean banned) {
        userService.Ban(id, banned);
        return ResponseEntity.ok("User banned");
    }
    @PostMapping(value = "/unban")
    public ResponseEntity<?> unbanUser(@RequestParam(name = "user_id") Long id,
                                     @RequestParam(name = "banned") boolean unbanned) {
        userService.unBan(id, unbanned);
        return ResponseEntity.ok("User unbanned");
    }



    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.userListDTO());
    }

}