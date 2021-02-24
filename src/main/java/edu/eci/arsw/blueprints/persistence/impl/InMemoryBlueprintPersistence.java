/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistence;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 *
 * @author hcadavid
 */
@Component("InMemoryBlueprintPersistence")
public class InMemoryBlueprintPersistence implements BlueprintPersistence{

    private final Map<Tuple<String,String>,Blueprint> blueprints=new HashMap<>();

    public InMemoryBlueprintPersistence() {
        //load stub data
        //joel 1
        Point[] pts_joel=new Point[]{new Point(140, 150),new Point(115, 135)};
        Blueprint bp_joel=new Blueprint("joel", "joelplanouno",pts_joel);
        blueprints.put(new Tuple<>(bp_joel.getAuthor(),bp_joel.getName()), bp_joel);

        //joel 2
        Point[] pts_joel_2=new Point[]{new Point(140, 140),new Point(140, 140)};
        Blueprint bp_joel_2=new Blueprint("joel", "joelplanodos",pts_joel_2);
        blueprints.put(new Tuple<>(bp_joel_2.getAuthor(),bp_joel_2.getName()), bp_joel_2);

        //anonimo
        //joel 2
        Point[] pts_anonimo=new Point[]{new Point(140, 140),new Point(150, 140)};
        Blueprint bp_anonimo=new Blueprint("anonimo", "anonimoplano",pts_anonimo);
        blueprints.put(new Tuple<>(bp_anonimo.getAuthor(),bp_anonimo.getName()), bp_anonimo);

    }

    @Override
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        if (blueprints.containsKey(new Tuple<>(bp.getAuthor(),bp.getName()))){
            throw new BlueprintPersistenceException("The given blueprint already exists: "+bp);
        }
        else{
            blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        }
    }

    @Override
    public Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException {
        return blueprints.get(new Tuple<>(author, bprintname));
    }

    @Override
    public Set<Blueprint> getBluePrintsByAuthor(String author) {
        Set<Blueprint> blueprintsbyauthor = new HashSet<Blueprint>();
        for(Tuple<String,String> authorbpname: blueprints.keySet ()){
            if(authorbpname.getElem1().equals(author)){
                blueprintsbyauthor.add (blueprints.get (authorbpname));
            }
        }
        return blueprintsbyauthor;
    }

    @Override
    public Set<Blueprint> getAllBlueprints() {
        Set<Blueprint> allBlueprints = new HashSet<Blueprint>();
        for(Tuple<String,String> key: blueprints.keySet ()){
            allBlueprints.add (blueprints.get (key));
        }
        return allBlueprints;
    }

    @Override
    public void updateBlueprint(Blueprint blueprint, String author, String name) throws BlueprintNotFoundException {
        Blueprint tempBlueprint = getBlueprint(author,name);
        tempBlueprint.setPoints (blueprint.getPoints ());
        tempBlueprint.setName (blueprint.getName ());
        tempBlueprint.setAuthor (blueprint.getAuthor ());
    }


}
