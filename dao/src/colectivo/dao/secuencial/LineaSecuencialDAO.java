package colectivo.dao.secuencial;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.Hashtable;
import java.util.ResourceBundle;
import java.util.Scanner;

import colectivo.conexion.Factory;
import colectivo.dao.LineaDao;
import colectivo.modelo.Linea;
import colectivo.modelo.Parada;
import net.datastructures.Entry;
import net.datastructures.TreeMap;
import colectivo.dao.ParadaDao;

public class LineaSecuencialDAO implements LineaDao {

	private TreeMap<String, Linea> lineas;
	private Hashtable<String, Parada> paradas;
	private String name;
	private boolean actualizar;

	public LineaSecuencialDAO() {
		lineas = new TreeMap<String, Linea>();
		ResourceBundle rb = ResourceBundle.getBundle("secuencial");
		paradas = cargarParadas();
		name = rb.getString("linea");
		actualizar = true;
	}
	
	
	
	private TreeMap<String, Linea> readFromFile(String file) {
		Scanner inFile = null;
		TreeMap<String, Linea> lineas = new TreeMap<String, Linea>();

		
		try {
			inFile = new Scanner(new File(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String registro;
		Linea linea;
		while (inFile.hasNext()) {
			registro = inFile.next();
			String[] campos = registro.split(";");
			linea = lineas.get(campos[0]);
			if (linea == null) {
				linea = new Linea(campos[0]);
				lineas.put(campos[0], linea);
			}
			if (campos[1].equals("I"))
				for (int i = 2; i < campos.length; i++) {
					
					linea.agregarParadaIda(paradas.get(campos[i]));
				}
					
			if (campos[1].equals("R"))
				for (int i = 2; i < campos.length; i++)
					linea.agregarParadaRegreso(paradas.get(campos[i]));
		}
		inFile.close();

		return lineas;
	}
/*
	private TreeMap<String, Linea> readFromFile(String file) {
		 lineas = new TreeMap<String, Linea>();

		Scanner inFile = null;
		try {
			inFile = new Scanner(new File(file));
			inFile.useDelimiter("\\s*;\\s*");
			while (inFile.hasNextLine()) {
				String lineaInfo = inFile.nextLine();
				String[] parts = lineaInfo.split(";");
				if (parts.length >= 3) {
					String nombre = parts[0].trim();
					String sentido = parts[1].trim();
					String[] paradasIdsStr = parts[2].split(";");
					Integer[] paradasIds = new Integer[paradasIdsStr.length];
					for (int i = 0; i < paradasIdsStr.length; i++) {
						paradasIds[i] = Integer.parseInt(paradasIdsStr[i].trim());
					}
					Linea linea = new Linea(nombre);
					if (lineas.get(nombre)!=null) {
						linea=lineas.get(nombre);
					}
					for (Integer paradaId : paradasIds) {
						Parada parada = paradas.get(paradaId);
						if (parada != null) {
							if ("I".equals(sentido)) {
								linea.agregarParadaIda(parada);
							} else if ("R".equals(sentido)) {
								linea.agregarParadaRegreso(parada);
							}
						}

					}
					lineas.put(nombre, linea);
				}
			}
		} catch (FileNotFoundException fileNotFoundException) {
			System.err.println("Error creating file.");
		} catch (FormatterClosedException formatterClosedException) {
			System.err.println("Error writing to file.");
		} finally {
			if (inFile != null)
				inFile.close();
		}
		
		return lineas;
	}
*/
	private void writeToFile(TreeMap<String, Linea> lineas, String file) {
		Formatter outFile = null;
		try {
			outFile = new Formatter(file);
			for (Linea e : lineas.values()) {
				outFile.format("%s;%s;\n", e.getNombre());
			}
		} catch (FileNotFoundException fileNotFoundException) {
			System.err.println("Error creating file.");
		} catch (FormatterClosedException formatterClosedException) {
			System.err.println("Error writing to file.");
		} finally {
			if (outFile != null)
				outFile.close();
		}
	}

	@Override
	public TreeMap<String, Linea> buscarTodos() {
		if (actualizar) {
			lineas = readFromFile(name);
			actualizar = false;
		}
		return lineas;
	}

	@Override
	public void insertar(Linea linea) {
		lineas.put(linea.getNombre(), linea);
		writeToFile(lineas, name);
		actualizar = true;
	}

	@Override
	public void actualizar(Linea linea) {
		lineas.put(linea.getNombre(), linea);
		writeToFile(lineas, name);
		actualizar = true;
	}

	@Override
	public void borrar(Linea linea) {
		lineas.remove(linea.getNombre());
		writeToFile(lineas, name);
		actualizar = true;
	}

	public Hashtable<String, Parada> cargarParadas() {
		Hashtable<String, Parada> paradas = new Hashtable<String, Parada>();
		ParadaDao paradaDAO = (ParadaSecuencialDAO) Factory.getInstancia("PARADA");
		TreeMap<String, Parada> ds = paradaDAO.buscarTodos();
		for (Parada d : ds.values()) {
			paradas.put(d.getId(), d);
			
		}
	
		return paradas;
	}
}
