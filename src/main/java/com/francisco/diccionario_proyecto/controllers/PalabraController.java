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
@RequestMapping("/palabra")
public class PalabraController {

    @Autowired
    private PalabraService palabraService;

    @Autowired
    private DefinicionService definicionService;

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

    @GetMapping("/{id}/definiciones")
    public ResponseEntity<List<Definicion>> getDefinicionesByPalabraId(@PathVariable Long id) {
        List<Definicion> definiciones = definicionService.getDefinicionesByPalabraId(id);
        return ResponseEntity.status(HttpStatus.OK).body(definiciones);
    }

    @PostMapping("/{id}/definiciones")
    public ResponseEntity<Definicion> addDefinicionToPalabra(@PathVariable Long id, @RequestBody Definicion newDefinicion) {
        Definicion definicion = definicionService.addDefinicionToPalabra(id, newDefinicion);
        return ResponseEntity.status(HttpStatus.CREATED).body(definicion);
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Palabra>> getPalabrasByCategoria(@PathVariable String categoria) {
        List<Palabra> palabras = palabraService.getPalabrasByCategoria(categoria);
        if (palabras.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity<List<Palabra>>(palabras, new HttpHeaders(), HttpStatus.OK);

    }

    @GetMapping("/inicial/{letra}")
    public List<Palabra> buscarPorInicial(@PathVariable char letra) {
        return palabraService.buscarPorInicial(letra);
    }

    @PostMapping("/condefiniciones")
    public ResponseEntity<Palabra> crearPalabraConDefiniciones(@RequestBody Palabra palabra) {
        Palabra nuevaPalabra = palabraService.crearPalabraConDefiniciones(palabra);
        System.out.println(palabra.getDefinicions());
        return new ResponseEntity<>(nuevaPalabra, HttpStatus.CREATED);
    }
}
