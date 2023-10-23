package colectivo.dao;

import net.datastructures.TreeMap;

import colectivo.modelo.Linea;

public interface LineaDao {
	
		void insertar(Linea linea);

		void actualizar(Linea linea);

		void borrar(Linea linea);

		TreeMap<String, Linea> buscarTodos();
	}

