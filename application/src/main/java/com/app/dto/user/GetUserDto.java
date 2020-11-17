package com.app.dto.user;

import com.app.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetUserDto {

    private Long id;
    private String username;
    private String email;
    private Role role;
}
