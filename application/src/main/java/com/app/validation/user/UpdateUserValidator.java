package com.app.validation.user;

import com.app.dto.user.UpdateUserDto;
import com.app.user.Role;
import com.app.validation.generic.Validator;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UpdateUserValidator implements Validator<UpdateUserDto> {

    @Override
    public Map<String, String> validate(UpdateUserDto item) {
        var errors = new HashMap<String, String>();

        if (Objects.isNull(item)) {
            errors.put("object", "object is null");
            return errors;
        }

        if (!isUserIdValid(item.getId())) {
            errors.put("id", "id is not valid");
        }

        if (!isUsernameValid(item.getUsername())) {
            errors.put("username", "username is not valid");
        }

        if (!isEmailValid(item.getEmail())) {
            errors.put("email", "email is not valid");
        }

        if (!isPasswordValid(item.getPassword(), item.getPasswordConfirmation())) {
            errors.put("password", "password is not valid");
        }

        if (!isRoleValid(item.getRole())) {
            errors.put("role", "role is not valid");
        }

        return errors;
    }

    private boolean isUserIdValid(Long id) {
        return id != null && id > 0;
    }

    private boolean isRoleValid(Role role) {
        return role != null;
    }

    private boolean isPasswordValid(String password, String passwordConfirmation) {
        return password != null && password.equals(passwordConfirmation);
    }

    private boolean isEmailValid(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    private boolean isUsernameValid(String username) {
        return username != null && username.matches("[A-z0-9_]+");
    }
}
