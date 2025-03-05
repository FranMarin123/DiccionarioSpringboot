package com.francisco.diccionario_proyecto.services;

import com.francisco.diccionario_proyecto.exceptions.RecordNotFoundException;
import com.francisco.diccionario_proyecto.models.Palabra;
import com.francisco.diccionario_proyecto.repositories.PalabraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PalabraService {
    @Autowired
    private PalabraRepository palabraRepository;
    public List<Palabra> getAllPalabras() {
        List<Palabra> palabraList = palabraRepository.findAll();
        if(palabraList.size()>0){
            return palabraList;
        }else{
            return new ArrayList<Palabra>();
        }
    }

    public Palabra getPalabraById(Long id) throws RecordNotFoundException {
        Optional<Palabra> palabra = palabraRepository.findById(id);
        if(palabra.isPresent()){
            return palabra.get();
        }else{
            throw new RecordNotFoundException("No existe palabra para el id: ",id);
        }
    }
    public Palabra createPalabra(Palabra palabra) {
        palabra = palabraRepository.save(palabra);
        return palabra;
    }
    public Palabra updatePalabra(Long id, Palabra palabra) throws RecordNotFoundException {
        //if (palabra.getId()!=null){
            Optional<Palabra> movieOptional = palabraRepository.findById(id);
            if (movieOptional.isPresent()){
                Palabra newPalabra = movieOptional.get();
                newPalabra.setDefinicions(palabra.getDefinicions());
                newPalabra.setTermino(palabra.getTermino());
                newPalabra.setCategoriaGramatical(palabra.getCategoriaGramatical());
                newPalabra=palabraRepository.save(newPalabra);
                return newPalabra;
            }else{
                throw new RecordNotFoundException("No existe Palabra para el id:",palabra.getId());
            }
        //}else{
        //    throw new RecordNotFoundException("No hay id en la palabra a actualizar ",0l);
        //}
    }
    public void deletePalabra(Long id) throws RecordNotFoundException {
        Optional<Palabra> palabraOptional = palabraRepository.findById(id);
        if (palabraOptional.isPresent()){
            palabraRepository.delete(palabraOptional.get());
        }else{
            throw new RecordNotFoundException("No existe Palabra para el id: ",id);
        }
    }
}
