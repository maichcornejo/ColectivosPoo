package colectivo.interfaz;

import java.util.List;

import colectivo.controlador.Constantes;
import colectivo.controlador.Coordinador;
import colectivo.modelo.Linea;
import colectivo.modelo.Parada;
import colectivo.modelo.Tramo;
import net.datastructures.TreeMap;

public class Interfaz {

	private Coordinador coordinador;
	
	// Usuario ingresa opcion
	public static int opcion() {
		return Constantes.MAS_RAPIDO;
	}

	// Usuario ingresa parada origen
	public static Parada ingresarParadaOrigen(TreeMap<String, Parada> paradas) {
		return paradas.get("1");
	}

	// Usuario ingresa parada destino
	public static Parada ingresarParadaDestino(TreeMap<String, Parada> paradas) {
		return paradas.get("27");
	}

	public static void resultado(List<Tramo> recorrido) {
	    int tiempoTotal = 0;
	    Linea lineaInicio, lineaFin;
	    String nombreLineaInicio, nombreLineaFin;
	    
	    for (Tramo t : recorrido) {
	        // Comprobar si la parada de inicio tiene líneas
	        if (t.getInicio().getLineas() != null && !t.getInicio().getLineas().isEmpty()) {
	            lineaInicio = t.getInicio().getLineas().get(t.getInicio().getLineas().size() - 1);
	            nombreLineaInicio = "Caminando";
	            if (lineaInicio != null) {
	                nombreLineaInicio = lineaInicio.getNombre();
	            }
	        } else {
	            nombreLineaInicio = "Caminando";
	        }
	        // Comprobar si la parada de fin tiene líneas
	        if (t.getFin().getLineas() != null && !t.getFin().getLineas().isEmpty()) {
	            lineaFin = t.getFin().getLineas().get(t.getFin().getLineas().size() - 1);
	            nombreLineaFin = "Caminando";
	            if (lineaFin != null) {
	                nombreLineaFin = lineaFin.getNombre();
	            }
	        } else {
	            nombreLineaFin = "Caminando";
	        }

	        System.out.println(t.getInicio().getId() + " " + nombreLineaInicio + " > "
	                + t.getFin().getId() + " " + nombreLineaFin + " :" + t.getTiempo());

	        tiempoTotal += t.getTiempo();
	    }

	    System.out.println("Tiempo Total: " + tiempoTotal);
	}

	
	
	public void setCoordinador(Coordinador coordinador) {
		this.coordinador = coordinador;
	}

}
