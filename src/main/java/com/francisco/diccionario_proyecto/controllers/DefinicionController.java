package com.francisco.diccionario_proyecto.controllers;

import com.francisco.diccionario_proyecto.exceptions.RecordNotFoundException;
import com.francisco.diccionario_proyecto.models.Definicion;
import com.francisco.diccionario_proyecto.models.Palabra;
import com.francisco.diccionario_proyecto.services.DefinicionService;
import com.francisco.diccionario_proyecto.services.PalabraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/definicion")
public class DefinicionController {
    @Autowired
    private DefinicionService definicionService;

    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<Definicion>> findAll() {
        List<Definicion> list = definicionService.getAllDefiniciones();
        return new ResponseEntity<List<Definicion>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Definicion> getDefinicionById(@PathVariable Long id) throws RecordNotFoundException {
        Definicion definicion = definicionService.getDefinicionById(id);
        return new ResponseEntity<Definicion>(definicion, new HttpHeaders(), HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/{palabraId}")
    public ResponseEntity<Definicion> createDefinicion(@PathVariable Long palabraId,@RequestBody Definicion definicion) {
        Definicion createdDefinicion = definicionService.createDefinicion(palabraId,definicion);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDefinicion);
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<Definicion> updateDefinicion(@PathVariable Long id, @RequestBody Definicion updatedDefinicion) throws RecordNotFoundException {
        Definicion definicionUpdated = definicionService.updateDefinicion(id, updatedDefinicion);
        return ResponseEntity.status(HttpStatus.OK).body(definicionUpdated);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public HttpStatus deleteDefinicionbyId(@PathVariable Long id) throws RecordNotFoundException {
        definicionService.deleteDefinicion(id);
        return HttpStatus.ACCEPTED;
    }
}
