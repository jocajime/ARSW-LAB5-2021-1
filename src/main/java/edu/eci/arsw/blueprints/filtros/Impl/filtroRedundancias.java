package edu.eci.arsw.blueprints.filtros.Impl;

import edu.eci.arsw.blueprints.filtros.IFiltro;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import org.springframework.stereotype.Component;

import java.util.*;

@Component("filtroRedundancias")
public class filtroRedundancias implements IFiltro{

    @Override
    public Set<Blueprint> filtroBP(Set<Blueprint> blueprints) {
        Set<Blueprint> blueprintsfiltrados =new HashSet<>();
        for(Blueprint blueprint:blueprints){
            blueprintsfiltrados.add (filtrar (blueprint));
        }
        return blueprintsfiltrados;

    }

    private Blueprint filtrar(Blueprint blueprint) {
        List<Point> points = blueprint.getPoints ();
        List<Point> newPoints = new ArrayList<> ();
        newPoints.add(points.get (0));
        for(int i = 1;i<points.size ();i++){
            if((points.get (i-1).getX () != points.get (i).getX ()) && (points.get (i-1).getY () != points.get (i).getY ())){
                newPoints.add (points.get (i));
            }
        }
        Blueprint resp = new Blueprint (blueprint.getAuthor (), blueprint.getName (), newPoints);

        return resp;
    }

}
