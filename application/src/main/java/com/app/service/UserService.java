package com.app.service;

import com.app.dto.user.CreateUserDto;
import com.app.dto.user.UpdateUserDto;
import com.app.exception.UserServiceException;
import com.app.mapper.Mapper;
import com.app.user.UserRepository;
import com.app.validation.user.CreateUserValidator;
import com.app.validation.user.UpdateUserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Long register(CreateUserDto createUserDto) {
        var createUserValidator = new CreateUserValidator();
        var errors = createUserValidator.validate(createUserDto);

        if (!errors.isEmpty()) {
            var errorMessage = errors
                    .entrySet()
                    .stream()
                    .map(e -> e.getKey() + ": " + e.getValue())
                    .collect(Collectors.joining(", "));
            throw new UserServiceException(errorMessage);
        }

        var user = Mapper.fromCreateUserToUser(createUserDto);

        //TODO dokonczyc
        return null;
    }

    public Long updateUser(UpdateUserDto updateUserDto) {
        var updateUserValidator = new UpdateUserValidator();
        var errors = updateUserValidator.validate(updateUserDto);
        if (!errors.isEmpty()) {
            var errorMessage = errors
                    .entrySet()
                    .stream()
                    .map(e -> e.getKey() + ": " + e.getValue())
                    .collect(Collectors.joining(", "));

            throw new UserServiceException(errorMessage);
        }

        var user = userRepository
                .findById(updateUserDto.getId())
                .orElseThrow(() -> new UserServiceException("Such com.app.user does not exist in database"));

        user.setEmail(updateUserDto.getEmail());
        user.setUsername(updateUserDto.getUsername());
        user.setRole(updateUserDto.getRole());
        user.setPassword(updateUserDto.getPassword());
        user = userRepository.addOrUpdate(user).orElseThrow(() -> new UserServiceException("User could not be saved to database"));
        return user.getId();
    }


}
