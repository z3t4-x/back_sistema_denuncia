package com.dev.dao;

import com.dev.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPersonaDAO extends JpaRepository<Persona, Integer>{

    //Persona findByDni(String dni);

   // Persona  findByApellido1OrApellido2LikeIgnoreCase(String apellido);
     Persona findOneByDni(String dni);

}
