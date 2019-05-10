package com.bird.controller;

import com.bird.dto.SignInDTO;
import com.bird.dto.UserDataDTO;
import com.bird.dto.UserResponseDTO;
import com.bird.model.User;
import com.bird.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/signin")

    public SignInDTO signin(@RequestBody SignInDTO signInDTO) {
        return userService.signin(signInDTO.getUsername(), signInDTO.getPassword());
    }

    @PostMapping("/signup")
    public String signup(@RequestBody UserDataDTO userDTO) {
        User user = new User();
        String[] ignoreProperties = {};
        BeanUtils.copyProperties(userDTO, user, ignoreProperties);
        return userService.signup(user);
    }

    @DeleteMapping(value = "/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")

    public String delete(@PathVariable String username) {
        userService.delete(username);
        return username;
    }

    @GetMapping(value = "/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")

    public UserResponseDTO search(@PathVariable String username) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        User user = userService.search(username);
        String[] ignoreProperties = {};
        BeanUtils.copyProperties(user, userResponseDTO, ignoreProperties);
        return userResponseDTO;
    }

    @GetMapping(value = "/me")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")

    public UserResponseDTO whoami(HttpServletRequest req) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        User user = userService.whoami(req);
        String[] ignoreProperties = {};
        BeanUtils.copyProperties(user, userResponseDTO, ignoreProperties);
        return userResponseDTO;
    }

}
