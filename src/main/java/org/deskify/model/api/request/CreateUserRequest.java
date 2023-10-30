package org.deskify.model.api.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.lang.NonNull;


@Data
@Builder
@AllArgsConstructor
public class CreateUserRequest {

    @NonNull
    @Schema(description = "User username", example = "TestAccount")
    private String username;

    @NonNull
    @Schema(description = "User first name", example = "Freddie")
    private String firstName;

    @NonNull
    @Schema(description = "User last name", example = "Mercury")
    private String lastName;

    @NonNull
    @Schema(description = "User Password", example = "123")
    private String password;

    @NonNull
    @Schema(description = "User email", example = "test@gmail.com")
    private String email;

    public CreateUserRequest() {

    }
}
