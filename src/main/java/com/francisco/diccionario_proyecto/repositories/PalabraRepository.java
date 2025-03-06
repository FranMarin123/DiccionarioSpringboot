package com.francisco.diccionario_proyecto.repositories;

import com.francisco.diccionario_proyecto.models.Palabra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PalabraRepository extends JpaRepository<Palabra, Long> {
    List<Palabra> findByCategoriaGramatical(String categoriaGramatical);
    @Query("SELECT p FROM Palabra p WHERE p.termino LIKE :prefix%")
    List<Palabra> findByNombreStartingWith(@Param("prefix") String prefix);
}
