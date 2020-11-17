package service;

import dto.user.CreateUserDto;
import dto.user.UpdateUserDto;
import exception.UserServiceException;
import lombok.RequiredArgsConstructor;
import mapper.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import user.UserRepository;
import validation.user.CreateUserValidator;
import validation.user.UpdateUserValidator;

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
                .orElseThrow(() -> new UserServiceException("Such user does not exist in database"));

        user.setEmail(updateUserDto.getEmail());
        user.setUsername(updateUserDto.getUsername());
        user.setRole(updateUserDto.getRole());
        user.setPassword(updateUserDto.getPassword());
        user = userRepository.addOrUpdate(user).orElseThrow(() -> new UserServiceException("User could not be saved to database"));
        return user.getId();
    }
}
