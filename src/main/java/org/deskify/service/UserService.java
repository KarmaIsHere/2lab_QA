package org.deskify.service;

import org.deskify.model.api.request.CreateUserRequest;
import org.deskify.model.domain.AccountType;
import org.deskify.model.domain.User;
import org.deskify.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(CreateUserRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .accountType(AccountType.USER)
                .email(request.getEmail())
                .build();

        return userRepository.save(user);
    }

    public List<User> fetchUsers(Long id, String username, String firstName, String lastName, String email) {
        return (id != null) ? this.userRepository.findAllById(id)
                : (username != null) ? this.userRepository.findAllByUsername(username)
                : (firstName != null) ? this.userRepository.findAllByFirstName(firstName)
                : (lastName != null) ? this.userRepository.findAllByLastName(lastName)
                : (email != null) ? this.userRepository.findAllByEmail(email)
                : this.userRepository.findAll();
    }

    public void updateUserInformation(Long id, String newUsername, String newPassword, String newFirstName, String newLastName, AccountType newAccountType, String newEmail) {
        User user = userRepository.findUserById(id);
        boolean userUpdated = false;

        if (newUsername != null) {
            user.setUsername(newUsername);
            userUpdated = true;
        }
        if (newPassword != null) {
            user.setPassword(newPassword);
            userUpdated = true;
        }
        if (newFirstName != null) {
            user.setFirstName(newFirstName);
            userUpdated = true;
        }
        if (newLastName != null) {
            user.setLastName(newLastName);
            userUpdated = true;
        }
        if (newAccountType != null) {
            user.setAccountType(newAccountType);
            userUpdated = true;
        }
        if (newEmail != null) {
            user.setEmail(newEmail);
            userUpdated = true;
        }
        if (userUpdated) {
            userRepository.save(user);
        }
    }

    public void deleteUserByUsername(Long id) {
        User user = userRepository.findUserById(id);
        userRepository.deleteById(user.getId());
    }
}
