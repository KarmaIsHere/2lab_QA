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

    // Creates user object
    public User create(CreateUserRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setAccountType(AccountType.USER);
        user.setEmail(request.getEmail());

        return userRepository.save(user);
    }

    // Fetches user by provided parameter
    public List<User> fetch(Long id, String username, String firstName,  String lastName,  String email) {
        if (id != null) {
            return this.userRepository.findAllById(id);
        } else if (username != null) {
            return this.userRepository.findAllByUsername(username);
        } else if (firstName != null) {
            return this.userRepository.findAllByFirstName(firstName);
        } else if (lastName != null) {
            return this.userRepository.findAllByLastName(lastName);
        } else if (email != null) {
            return this.userRepository.findAllByEmail(email);
        } else {
            return this.userRepository.findAll();
        }
    }

    // Updates user fields by provided parameters
    public void update(Long id, String Username, String Password, String FirstName, String LastName, AccountType AccountType, String Email) {
        User user = userRepository.findUserById(id);

        if (Username != null) {
            user.setUsername(Username);
        }
        if (Password != null) {
            user.setPassword(Password);
        }
        if (FirstName != null) {
            user.setFirstName(FirstName);
        }
        if (LastName != null) {
            user.setLastName(LastName);
        }
        if (AccountType != null) {
            user.setAccountType(AccountType);
        }
        if (Email != null) {
            user.setEmail(Email);
        }

        userRepository.save(user);
    }

    // Deletes provided user
    public void delete(Long id) {
        User user = userRepository.findUserById(id);
        userRepository.deleteById(user.getId());
    }
}