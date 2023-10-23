package colectivo.negocio;
import java.util.ArrayList;
import java.util.List;

import colectivo.modelo.Linea;
import colectivo.modelo.Parada;
import colectivo.modelo.Tramo;
import colectivo.servicio.LineaService;
import colectivo.servicio.LineaServiceImpl;
import colectivo.servicio.ParadaService;
import colectivo.servicio.ParadaServiceImpl;
import colectivo.servicio.TramoService;
import colectivo.servicio.TramoServiceImpl;
import net.datastructures.TreeMap;


public class Empresa {

    private static Empresa empresa = null;

    private String nombre;
    private TreeMap<String, Linea> lineas;
    private LineaService lineaService;
    private TreeMap<String, Parada> paradas;
    private ParadaService paradaService;
    private List<Tramo> tramos;
    private TramoService tramoService;

    public static Empresa getEmpresa() {
        if (empresa == null) {
            empresa = new Empresa();
        }
        return empresa;
    }

    private Empresa() {
        super();
        lineas = new TreeMap<String, Linea>();
        lineaService = new LineaServiceImpl();
        TreeMap<String, Linea> lineasData = lineaService.buscarTodos(); // Obtener datos de líneas

        for (Linea linea : lineasData.values()) { // Recorrer el TreeMap de líneas
            lineas.put(linea.getNombre(), linea);
        }

       
        paradas = new TreeMap<String, Parada>();
        paradaService = new ParadaServiceImpl();
        TreeMap<String, Parada> paradasData = paradaService.buscarTodos(); // Obtener datos de paradas

        for (Parada parada : paradasData.values()) { // Recorrer el TreeMap de paradas
            paradas.put(parada.getId(), parada);
        }

        tramos = new ArrayList<Tramo>();
        tramoService = new TramoServiceImpl();
        tramos.addAll(tramoService.buscarTodos());
    }


    public void agregarLinea(Linea linea) throws LineaExisteException {
        if (lineas.get(linea.getNombre()) != null) {
            throw new LineaExisteException();
        }
        lineas.put(linea.getNombre(), linea);
        lineaService.insertar(linea);
    }

    public void modificarLinea(Linea linea) {
        if (lineas.get(linea.getNombre()) != null) {
            lineas.put(linea.getNombre(), linea);
            lineaService.actualizar(linea);
        }
    }

    public void borrarLinea(Linea linea) {
        if (lineas.get(linea.getNombre()) != null) {
            lineas.remove(linea.getNombre());
            lineaService.borrar(linea);
        }
    }

    public Linea buscarLinea(Linea linea) {
        return lineas.get(linea.getNombre());
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TreeMap<String, Linea> getLineas() {
        return lineas;
    }

    public TreeMap<String, Parada> getParadas() {
        return paradas;
    }

    public List<Tramo> getTramos() {
        return tramos;
    }
}
