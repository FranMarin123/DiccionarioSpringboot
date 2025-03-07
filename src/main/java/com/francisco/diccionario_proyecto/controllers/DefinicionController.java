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

    /**
     * Este método GET muestra todas las definiciones
     * @return Devolvemos una respuesta de OK con las definiciones
     */
    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<Definicion>> findAll() {
        List<Definicion> list = definicionService.getAllDefiniciones();
        return new ResponseEntity<List<Definicion>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Este método GET recibe un id por parametro y devuelve la definicion con ese id
     * @param id Indicamos el id de la definicion
     * @return Devolvemos una respuesta de OK con la definicion
     */
    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Definicion> getDefinicionById(@PathVariable Long id) throws RecordNotFoundException {
        Definicion definicion = definicionService.getDefinicionById(id);
        return new ResponseEntity<Definicion>(definicion, new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Este método POST hace que podamos crear una definicion para una palabra
     * @param palabraId Indicamos el id de la palabra por parametro
     * @param definicion Indicamos en el body con JSON los datos de la definicion
     * @return Responde con una respuesta afirmativa con la definicion creada
     */
    @CrossOrigin
    @PostMapping("/{palabraId}")
    public ResponseEntity<Definicion> createDefinicion(@PathVariable Long palabraId,@RequestBody Definicion definicion) {
        Definicion createdDefinicion = definicionService.createDefinicion(palabraId,definicion);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDefinicion);
    }

    /**
     * Este método PUT nos permite actualizar una definicion
     * @param id Indicamos por parámetro el id de la definición que queremos actualizar
     * @param updatedDefinicion Pasamos en el body con JSON los nuevos datos de la definicion
     * @return Devolvemos una respuesta de OK con los datos actualizados
     * @throws RecordNotFoundException
     */
    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<Definicion> updateDefinicion(@PathVariable Long id, @RequestBody Definicion updatedDefinicion) throws RecordNotFoundException {
        Definicion definicionUpdated = definicionService.updateDefinicion(id, updatedDefinicion);
        return ResponseEntity.status(HttpStatus.OK).body(definicionUpdated);
    }

    /**
     * Este método DELETE nos permite eliminar una definicion
     * @param id Indicamos el id de la definicion que queremos eliminar
     * @return Devolvemos que se ha aceptado la petición
     * @throws RecordNotFoundException
     */
    @CrossOrigin
    @DeleteMapping("/{id}")
    public HttpStatus deleteDefinicionbyId(@PathVariable Long id) throws RecordNotFoundException {
        definicionService.deleteDefinicion(id);
        return HttpStatus.ACCEPTED;
    }

}
