package com.dev.dao;

import com.dev.domain.Catalogos;
import com.dev.domain.CatalogosValores;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICatalogoDAO extends JpaRepository<Catalogos, Integer> {


    Catalogos findByDsNombre(String nombreCatalogo);

}
