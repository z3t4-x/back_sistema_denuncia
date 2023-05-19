package com.dev.dao;

import com.dev.domain.Persona;
import com.dev.domain.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolDAO extends JpaRepository<Rol, Integer> {
}
