package org.deskify.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.deskify.model.api.request.CreateUserRequest;
import org.deskify.model.api.response.UserResponse;
import org.deskify.model.domain.AccountType;
import org.deskify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/registration")
    @Operation(summary = "Create new user in database")
    public Long createUser(@Validated @RequestBody CreateUserRequest request) {
        return userService.createUser(request).getId();
    }

    @GetMapping(value = "/fetch")
    @Operation(summary = "Get users from database")
    public List<UserResponse> fetchUsers(@RequestParam(required = false) Long id,
                                         @RequestParam(required = false) String username,
                                         @RequestParam(required = false) String firstName,
                                         @RequestParam(required = false) String lastName,
                                         @RequestParam(required = false) String email) {
        return userService.fetchUsers(id, username, firstName, lastName, email).stream()
                .map(p -> new UserResponse(p.getId(), p.getUsername(), p.getPassword(), p.getFirstName(), p.getAccountType(), p.getLastName(), p.getEmail()))
                .collect(Collectors.toList());
    }

    @PutMapping(path = "/update")
    @Operation(summary = "Update user information")
    public void updateUsername(@RequestParam Long id,
                               @RequestParam(required = false) String newUsername,
                               @RequestParam(required = false) String password,
                               @RequestParam(required = false) String firstName,
                               @RequestParam(required = false) String lastName,
                               @RequestParam(required = false) AccountType accountType,
                               @RequestParam(required = false) String email) {
        this.userService.updateUserInformation(id, newUsername, password, firstName, lastName, accountType, email);
    }

    @DeleteMapping(path = "/delete")
    @Operation(summary = "Delete user from database")
    public ResponseEntity<Void> deleteUserUsername(@RequestParam Long id) {
        userService.deleteUserByUsername(id);
        return ResponseEntity.noContent().build();
    }

}