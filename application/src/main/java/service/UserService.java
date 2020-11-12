package service;

import dto.user.CreateUserDto;
import exception.UserServiceException;
import lombok.RequiredArgsConstructor;
import mapper.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import user.UserRepository;
import validation.user.CreateUserValidator;

import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private UserRepository userRepository;

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
}
