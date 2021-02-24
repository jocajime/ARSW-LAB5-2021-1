package edu.eci.arsw.blueprints.filtros;

import edu.eci.arsw.blueprints.model.Blueprint;

import java.util.Set;

public interface IFiltro {

    public Set<Blueprint> filtroBP(Set<Blueprint> blueprints);
}
