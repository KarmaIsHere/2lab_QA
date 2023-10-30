package org.deskify.model.api.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.deskify.model.domain.AccountType;

@Data
@AllArgsConstructor
public class UserResponse {
    @Schema(description = "User id", example = "1")
    private Long id;

    @Schema(description = "User username", example = "user123")
    private String username;

    @Schema(description = "User password", example = "123")
    private String password;

    @Schema(description = "User first name", example = "Freddie")
    private String firstName;

    @Schema(description = "User working accountType", example = "Manager")
    private AccountType accountType;

    @Schema(description = "User last name", example = "Mercury")
    private String lastName;

    @Schema(description = "User email", example = "test@gmail.com")
    private String email;
}
