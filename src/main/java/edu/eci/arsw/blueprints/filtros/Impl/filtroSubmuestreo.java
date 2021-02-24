package edu.eci.arsw.blueprints.filtros.Impl;

import edu.eci.arsw.blueprints.filtros.IFiltro;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component("filtroSubmuestreo")
public class filtroSubmuestreo implements IFiltro{
    @Override
    public Set<Blueprint> filtroBP(Set<Blueprint> blueprints) {
        Set<Blueprint> blueprintsfiltrados =new HashSet<> ();
        for(Blueprint blueprint:blueprints){
            blueprintsfiltrados.add (filtrar (blueprint));
        }
        return blueprintsfiltrados;

    }

    private Blueprint filtrar(Blueprint blueprint) {
        List<Point> points = blueprint.getPoints ();
        List<Point> newPoints = new ArrayList<> ();
        for(int i = 0;i<points.size ();i+=2){
            newPoints.add (points.get (i));
        }
        Blueprint resp = new Blueprint (blueprint.getAuthor (), blueprint.getName (), newPoints);

        return resp;
    }
}
