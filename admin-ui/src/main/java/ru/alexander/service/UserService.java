package ru.alexander.service;

import java.util.List;
import java.util.Optional;

import ru.alexander.controller.repr.UserRepr;

public interface UserService {

    void save(UserRepr userRepr);

    List<UserRepr> findAll();

    Optional<UserRepr> findById(Long id);

    void delete(Long id);
}
