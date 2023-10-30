package org.deskify.repository;

import org.deskify.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();
    List<User> findAllById(Long id);
    List<User> findAllByEmail(String email);
    List<User> findAllByFirstName(String firstName);
    List<User> findAllByLastName(String lastName);
    List<User> findAllByUsername(String username);
    User findUserById(Long id);
}
