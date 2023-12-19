package com.labwork01.app.user.controller;

import com.labwork01.app.user.model.UserDTO;
import com.labwork01.app.user.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable Long id) {
        return new UserDTO(userService.findById(id));
    }

    @GetMapping
    public List<UserDTO> getUsers() {
        return userService.findAll().stream()
                .map(UserDTO::new)
                .toList();
    }
    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        return new UserDTO(userService.insert(userDTO));
    }

    @PutMapping("/{id}")
    public UserDTO updateUser(@RequestBody UserDTO userDTO) {
        return new UserDTO(userService.update(userDTO));
    }

    @DeleteMapping("/{id}")
    public UserDTO deleteUser(@PathVariable Long id) {
        return new UserDTO(userService.delete(id));
    }
}
