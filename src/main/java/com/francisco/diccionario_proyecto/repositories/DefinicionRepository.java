package com.francisco.diccionario_proyecto.repositories;

import com.francisco.diccionario_proyecto.models.Definicion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DefinicionRepository extends JpaRepository<Definicion, Long> {
    List<Definicion> findByPalabraId(Long palabraId);
}
