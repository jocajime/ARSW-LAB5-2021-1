/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.controllers;


import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.services.BlueprintServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author hcadavid
 */
@RestController
public class BlueprintAPIController {

    @Autowired
    @Qualifier("BlueprintServices")
    BlueprintServices bps;

    @RequestMapping(value = "/blueprints", method = RequestMethod.GET)
    public ResponseEntity<?> manejadorGetRecursoBlueprints() {
        try {
            return new ResponseEntity<> (bps.getAllBlueprints (), HttpStatus.ACCEPTED);
        }catch (Exception ex) {
            Logger.getLogger (BlueprintAPIController.class.getName ()).log (Level.SEVERE, null, ex);
            return new ResponseEntity<> ("no hay planos", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/blueprints/{author}",method = RequestMethod.GET)
    public ResponseEntity<?> manejadorGetRecursoBlueprintsByAuthor(@PathVariable String author) {
        try {
            return new ResponseEntity<> (bps.getBlueprintsByAuthor(author) , HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger (BlueprintAPIController.class.getName ()).log (Level.SEVERE, null, ex);
            return new ResponseEntity<> ("no se encontraron planos de este autor", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/blueprints/{author}/{bpname}",method = RequestMethod.GET)
    public ResponseEntity<?> manejadorGetRecursoBlueprint(@PathVariable String author,@PathVariable String bpname) {
        try {
            return new ResponseEntity<> (bps.getBlueprint (author, bpname) , HttpStatus.ACCEPTED);
        }  catch (Exception ex) {
            Logger.getLogger (BlueprintAPIController.class.getName ()).log (Level.SEVERE, null, ex);
            return new ResponseEntity<> ("no se encontro el plano", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/blueprints/nuevoplano", method = RequestMethod.POST)
    public ResponseEntity<?> manejadorPostRecursoAgregarPlano(@RequestBody Blueprint blueprint){
        try {
            bps.addNewBlueprint (blueprint);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }  catch (Exception ex) {
            Logger.getLogger (BlueprintAPIController.class.getName ()).log (Level.SEVERE, null, ex);
            return new ResponseEntity<> ("no se pudo crear el plano", HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(value = "/blueprints/{author}/{name}", method = RequestMethod.PUT)
    public ResponseEntity<?> manejadorPutRecursoPlano(@RequestBody Blueprint blueprint, @PathVariable String author, @PathVariable String name) {
        try {
            bps.updateBlueprint ( blueprint, author, name);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}