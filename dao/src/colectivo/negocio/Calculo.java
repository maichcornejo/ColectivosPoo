package colectivo.negocio;

import java.util.ArrayList;
import java.util.List;

import net.datastructures.AdjacencyMapGraph;
import net.datastructures.Edge;
import net.datastructures.Entry;
import net.datastructures.Graph;
import net.datastructures.GraphAlgorithms;
import net.datastructures.Map;
import net.datastructures.Position;
import net.datastructures.PositionalList;
import net.datastructures.ProbeHashMap;
import net.datastructures.TreeMap;
import net.datastructures.Vertex;
import colectivo.controlador.Coordinador;
import colectivo.modelo.Parada;
import colectivo.modelo.Tramo;

public class Calculo {

    private Graph<Parada, Tramo> colectivo;
    private TreeMap<String, Vertex<Parada>> vertices;
	@SuppressWarnings("unused")
	private Coordinador coordinador;

    public Calculo() {
    }

    public void cargarDatos(List<Tramo> tramos, TreeMap<String, Parada> paradas) {
        colectivo = new AdjacencyMapGraph<>(false);

        // Cargar paradas
        vertices = new TreeMap<String, Vertex<Parada>>();
        for (Entry<String, Parada> entry : paradas.entrySet()) {
            Vertex<Parada> vertex = colectivo.insertVertex(entry.getValue());
            vertices.put(entry.getKey(), vertex);
        }
        

        // Cargar tramos
        for (Tramo tramo : tramos) {
            Vertex<Parada> inicio = vertices.get(tramo.getInicio().getId());
            Vertex<Parada> fin = vertices.get(tramo.getFin().getId());
            
            if (colectivo.getEdge(inicio, fin) == null) {
                // Verificar si la arista no existe antes de agregarla
                colectivo.insertEdge(inicio, fin, tramo);
            }
        }
    }


    public List<Tramo> rapido(Parada inicio, Parada fin) {
        // Copiar el grafo
        Graph<Parada, Integer> rapido = new AdjacencyMapGraph<>(false);
        Map<Parada, Vertex<Parada>> res = new ProbeHashMap<>();

        for (Vertex<Parada> result : colectivo.vertices())
            res.put(result.getElement(),
                    rapido.insertVertex(result.getElement()));

        Vertex<Parada>[] vert;

        for (Edge<Tramo> result : colectivo.edges()) {
            vert = colectivo.endVertices(result);
            rapido.insertEdge(res.get(vert[0].getElement()), res.get(vert[1]
                    .getElement()), result.getElement().getTiempo());
        }
        PositionalList<Vertex<Parada>> lista = GraphAlgorithms
                .shortestPathList(rapido, res.get(inicio),
                        res.get(fin));

        List<Tramo> tramos = new ArrayList<Tramo>();

        Vertex<Parada> v1, v2;
        Position<Vertex<Parada>> aux = lista.first();
        while (lista.after(aux) != null) {
            v1 = aux.getElement();
            aux = lista.after(aux);
            v2 = aux.getElement();
            tramos.add(colectivo.getEdge(vertices.get(v1.getElement().getId()),
                    vertices.get(v2.getElement().getId())).getElement());
        }
        return tramos;
    }

    public void setCoordinador(Coordinador coordinador) {
		this.coordinador = coordinador;
	}
}
