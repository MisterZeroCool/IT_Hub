package ru.alexander.persist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import ru.alexander.persist.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByName(String name);

    boolean existsUserByEmail(String email);

    Optional<User> findUserByEmail(String email);
}
