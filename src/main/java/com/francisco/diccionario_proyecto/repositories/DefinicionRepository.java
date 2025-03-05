package com.francisco.diccionario_proyecto.repositories;

import com.francisco.diccionario_proyecto.models.Definicion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DefinicionRepository extends JpaRepository<Definicion, Long> {
}
