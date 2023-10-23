package colectivo.dao;

import net.datastructures.TreeMap;
import colectivo.modelo.Parada;

public interface ParadaDao {

	void insertar(Parada parada);

	void actualizar(Parada parada);

	void borrar(Parada parada);

	TreeMap<String, Parada> buscarTodos();

}
