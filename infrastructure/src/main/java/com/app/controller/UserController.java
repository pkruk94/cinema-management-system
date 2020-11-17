package com.app.controller;

import com.app.dto.response.ResponseData;
import com.app.dto.user.CreateUserDto;
import com.app.dto.user.UpdateUserDto;
import com.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseData<Long> register(CreateUserDto createUserDto) {
        return ResponseData.<Long>builder()
                .data(userService.register(createUserDto))
                .build();
    }

    @PutMapping
    public ResponseData<Long> updateUser(UpdateUserDto updateUserDto) {
        return ResponseData.<Long>builder()
                .data(userService.updateUser(updateUserDto))
                .build();
    }
}
