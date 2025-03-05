package com.francisco.diccionario_proyecto.controllers;

import com.francisco.diccionario_proyecto.exceptions.RecordNotFoundException;
import com.francisco.diccionario_proyecto.models.Palabra;
import com.francisco.diccionario_proyecto.services.PalabraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/palabra")
public class PalabraController {
    @Autowired
    private PalabraService palabraService;

    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<Palabra>> findAll() {
        List<Palabra> list = palabraService.getAllPalabras();
        return new ResponseEntity<List<Palabra>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Palabra> getPalabraById(@PathVariable Long id) throws RecordNotFoundException {
        Palabra palabra = palabraService.getPalabraById(id);
        return new ResponseEntity<Palabra>(palabra, new HttpHeaders(), HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Palabra> createPalabra(@RequestBody Palabra palabra) {
        Palabra createdPalabra = palabraService.createPalabra(palabra);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPalabra);
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<Palabra> updatePalabra(@PathVariable Long id, @RequestBody Palabra updatedPalabra) throws RecordNotFoundException {
        Palabra palabraUpdated = palabraService.updatePalabra(id, updatedPalabra);
        return ResponseEntity.status(HttpStatus.OK).body(palabraUpdated);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public HttpStatus deletePalabrabyId(@PathVariable Long id) throws RecordNotFoundException {
        palabraService.deletePalabra(id);
        return HttpStatus.ACCEPTED;
    }

}
