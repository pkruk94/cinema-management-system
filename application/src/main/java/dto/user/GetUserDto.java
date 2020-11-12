package dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import user.Role;

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
