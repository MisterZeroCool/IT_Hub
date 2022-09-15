package ru.alexander.persist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.alexander.persist.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
