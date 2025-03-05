package com.francisco.diccionario_proyecto.repositories;

import com.francisco.diccionario_proyecto.models.Palabra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PalabraRepository extends JpaRepository<Palabra, Long> {
}
