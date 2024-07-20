package zw.co.jugaad.pcivault.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zw.co.jugaad.pcivault.models.dtos.ConsumableUser;
import zw.co.jugaad.pcivault.models.entities.User;
import zw.co.jugaad.pcivault.services.UserService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public User registerUser(@RequestBody ConsumableUser consumableUser){
        return userService.registerUser(consumableUser);
    }
}
