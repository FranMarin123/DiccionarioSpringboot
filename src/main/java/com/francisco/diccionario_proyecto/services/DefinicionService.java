package com.francisco.diccionario_proyecto.services;

import com.francisco.diccionario_proyecto.exceptions.RecordNotFoundException;
import com.francisco.diccionario_proyecto.models.Definicion;
import com.francisco.diccionario_proyecto.models.Palabra;
import com.francisco.diccionario_proyecto.repositories.DefinicionRepository;
import com.francisco.diccionario_proyecto.repositories.PalabraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DefinicionService {

    @Autowired
    private DefinicionRepository definicionRepository;

    @Autowired
    private PalabraRepository palabraRepository;

    public List<Definicion> getAllDefiniciones() {
        List<Definicion> definiciones = definicionRepository.findAll();
        if (definiciones.size() > 0) {
            return definiciones;
        } else {
            return new ArrayList<>();
        }
    }

    public Definicion getDefinicionById(Long id) throws RecordNotFoundException {
        Optional<Definicion> definicion = definicionRepository.findById(id);
        if (definicion.isPresent()) {
            return definicion.get();
        } else {
            throw new RecordNotFoundException("No existe definición para el id: ", id);
        }
    }

    public Definicion createDefinicion(Long palabraId, Definicion definicion) throws RecordNotFoundException {
        Optional<Palabra> palabraOptional = palabraRepository.findById(palabraId);
        if (palabraOptional.isPresent()) {
            definicion.setPalabra(palabraOptional.get());
            return definicionRepository.save(definicion);
        } else {
            throw new RecordNotFoundException("No existe la palabra con id: ", palabraId);
        }
    }

    public Definicion updateDefinicion(Long id, Definicion definicion) throws RecordNotFoundException {
        //if (definicion.getId() != null) {
            Optional<Definicion> definicionOptional = definicionRepository.findById(id);
            if (definicionOptional.isPresent()) {
                Definicion newDefinicion = definicionOptional.get();
                newDefinicion.setDescripcion(definicion.getDescripcion());
                newDefinicion.setEjemplo(definicion.getEjemplo());
                newDefinicion = definicionRepository.save(newDefinicion);
                return newDefinicion;
            } else {
                throw new RecordNotFoundException("No existe Definición para el id:", definicion.getId().longValue());
            }
        //} else {
        //    throw new RecordNotFoundException("No hay id en la definición a actualizar ", 0L);
        //}
    }

    public void deleteDefinicion(Long id) throws RecordNotFoundException {
        Optional<Definicion> definicionOptional = definicionRepository.findById(id);
        if (definicionOptional.isPresent()) {
            definicionRepository.delete(definicionOptional.get());
        } else {
            throw new RecordNotFoundException("No existe Definición para el id: ", id);
        }
    }

    public List<Definicion> getDefinicionesByPalabraId(Long palabraId) throws RecordNotFoundException {
        Optional<Palabra> palabra = palabraRepository.findById(palabraId);
        if (palabra.isPresent()) {
            return definicionRepository.findByPalabraId(palabraId);
        }
        return null;
    }

    public Definicion addDefinicionToPalabra(Long palabraId, Definicion newDefinicion) {
        Palabra palabra = palabraRepository.findById(palabraId).orElse(null);
        if (palabra == null) {
            return null;
        }
        newDefinicion.setPalabra(palabra);
        return definicionRepository.save(newDefinicion);
    }
}
