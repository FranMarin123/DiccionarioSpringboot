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

    /**
     * Este método GET nos muestra todas las palabras
     * @return Devolvemos una respuesta afirmativa con todas las palabras
     */
    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<Palabra>> findAll() {
        List<Palabra> list = palabraService.getAllPalabras();
        for (Palabra palabra : list) {
            palabra.setDefinicions(null);
        }
        return new ResponseEntity<List<Palabra>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Este método GET nos permite mostrar una palabra con el id indicado
     * @param id Pasamos por parámetro el id de la palabra
     * @return Devolvemos una respuesta afirmativa con la palabra
     */
    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Palabra> getPalabraById(@PathVariable Long id) throws RecordNotFoundException {
        Palabra palabra = palabraService.getPalabraById(id);
        palabra.setDefinicions(null);
        return new ResponseEntity<Palabra>(palabra, new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Este método POST nos permite registrar una palabra
     * @param palabra Pasamos en el body con JSON los datos de la palabra
     * @return Devolvemos una respuesta de que se ha creado con los datos de la palabra creada
     */
    @CrossOrigin
    @PostMapping
    public ResponseEntity<Palabra> createPalabra(@RequestBody Palabra palabra) {
        Palabra createdPalabra = palabraService.createPalabra(palabra);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPalabra);
    }

    /**
     * Este método PUT nos permite actualizar una palabra
     * @param id Recibimos por parámetro el id de la palabra que queremos modificar
     * @param updatedPalabra En el body pasamos con JSON los nuevos datos de la palabra
     * @return Devolvemos una respuesta OK con los nuevos datos de la palabra
     */
    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<Palabra> updatePalabra(@PathVariable Long id, @RequestBody Palabra updatedPalabra) throws RecordNotFoundException {
        Palabra palabraUpdated = palabraService.updatePalabra(id, updatedPalabra);
        return ResponseEntity.status(HttpStatus.OK).body(palabraUpdated);
    }

    /**
     * Este método DELETE nos permite eliminar palabras indicando el id por parámetro
     * @param id Indicamos el id de la palabra que queremos eliminar
     * @return
     */
    @CrossOrigin
    @DeleteMapping("/{id}")
    public HttpStatus deletePalabrabyId(@PathVariable Long id) throws RecordNotFoundException {
        palabraService.deletePalabra(id);
        return HttpStatus.ACCEPTED;
    }

    /**
     * Este método GET nos permite ver las definiciones de una palabra determinada
     * @param id Indicamos por parámetro el id de la palabra de la que queremos saber las definiciones
     * @return Devolvemos una respuesta OK con las definiciones de la palabra indicada
     */
    @GetMapping("/{id}/definiciones")
    public ResponseEntity<List<Definicion>> getDefinicionesByPalabraId(@PathVariable Long id) {
        List<Definicion> definiciones = definicionService.getDefinicionesByPalabraId(id);
        return ResponseEntity.status(HttpStatus.OK).body(definiciones);
    }

    /**
     * Este método POST nos permite guardar definiciones para una palabra indicada por parámetro
     * @param id Indicamos el id de la palabra por parámetro
     * @param newDefinicion indicamos en el body mediante JSON los datos de la nueva definicion
     * @return Devolvemos una respuesta de que se ha creado con los datos de la definicion
     */
    @PostMapping("/{id}/definiciones")
    public ResponseEntity<Definicion> addDefinicionToPalabra(@PathVariable Long id, @RequestBody Definicion newDefinicion) {
        Definicion definicion = definicionService.addDefinicionToPalabra(id, newDefinicion);
        return ResponseEntity.status(HttpStatus.CREATED).body(definicion);
    }

    /**
     * Este método GET nos permite buscar las palabras por categoria
     * @param categoria Indicamos por parámetro la categoria de palabra que queremos buscar
     * @return Devolvemos una respuesta OK con las palabras que cumplan con la categoria
     */
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Palabra>> getPalabrasByCategoria(@PathVariable String categoria) {
        List<Palabra> palabras = palabraService.getPalabrasByCategoria(categoria);
        return new ResponseEntity<List<Palabra>>(palabras, new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Este método POST nos permite buscar por la letra de inicio de las palabras
     * @param letra Indicamos la letra por la que queremos buscar
     * @return Devolvemos una respuesta OK con las palabras que comiencen con la letra introducida
     */
    @GetMapping("/inicial/{letra}")
    public List<Palabra> buscarPorInicial(@PathVariable char letra) {
        return palabraService.buscarPorInicial(letra);
    }

    /**
     * Este método POST nos permite crear una palabra con sus definiciones
     * @param palabra En el body introducimos mediante JSON la palabra con todas sus definiciones
     * @return Devolvemos que se ha creado con los datos de la palabra
     */
    @PostMapping("/condefiniciones")
    public ResponseEntity<Palabra> crearPalabraConDefiniciones(@RequestBody Palabra palabra) {
        Palabra nuevaPalabra = palabraService.crearPalabraConDefiniciones(palabra);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaPalabra);
    }
}
