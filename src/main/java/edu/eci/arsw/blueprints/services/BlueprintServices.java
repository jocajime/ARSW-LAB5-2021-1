/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.services;

import edu.eci.arsw.blueprints.filtros.IFiltro;
import edu.eci.arsw.blueprints.model.Blueprint;

import java.util.*;

import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistence;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author hcadavid
 */
@Service("BlueprintServices")
public class BlueprintServices {

    @Autowired
    @Qualifier("InMemoryBlueprintPersistence")
    BlueprintPersistence bpp;

    @Autowired
    @Qualifier("filtroRedundancias")
    IFiltro filtro;


    /**
     * @param bp
     * @throws BlueprintPersistenceException
     */
    public void addNewBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        bpp.saveBlueprint (bp);
    }

    /**
     * @return
     */
    public Set<Blueprint> getAllBlueprints(){
        return bpp.getAllBlueprints ();
    }

    /**
     *
     * @param author blueprint's author
     * @param name blueprint's name
     * @return the blueprint of the given name created by the given author
     * @throws BlueprintNotFoundException if there is no such blueprint
     */
    public Blueprint getBlueprint(String author,String name) throws BlueprintNotFoundException {
        return bpp.getBlueprint (author,name);
    }

    /**
     *
     * @param author blueprint's author
     * @return all the blueprints of the given author
     * @throws BlueprintNotFoundException if the given author doesn't exist
     */
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException{
        return filtro.filtroBP(bpp.getBluePrintsByAuthor(author));
    }

    /**
     * @return
     */
    public Set<Blueprint> getAllBlueprintsFiltro(){
        Set<Blueprint> blueprints = getAllBlueprints ();
        return filtro.filtroBP (blueprints);
    }


    /**
     * @param bpp
     */
    public void setBpp(BlueprintPersistence bpp) {this.bpp = bpp; }

    /**
     * @param filtro
     */
    public void setFiltro(IFiltro filtro) {this.filtro = filtro; }

    /**
     * @param blueprint
     * @param author
     * @param name
     * @throws BlueprintNotFoundException
     */
    public void updateBlueprint(Blueprint blueprint, String author, String name) throws BlueprintNotFoundException{
        bpp.updateBlueprint(blueprint,author,name);
    }

}
