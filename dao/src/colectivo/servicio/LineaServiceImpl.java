package colectivo.servicio;


import net.datastructures.TreeMap;
import colectivo.conexion.Factory;
import colectivo.dao.LineaDao;
import colectivo.dao.secuencial.LineaSecuencialDAO;
import colectivo.modelo.Linea;

public class LineaServiceImpl implements LineaService {

	private LineaDao lineaDao; 
		
	public LineaServiceImpl(){
		lineaDao = (LineaSecuencialDAO)Factory.getInstancia("LINEA");
	}
	
	@Override
	public void insertar(Linea linea) {
		lineaDao.insertar(linea);				
	}

	@Override
	public void actualizar(Linea linea) {
		lineaDao.actualizar(linea);						
	}

	@Override
	public void borrar(Linea linea) {
		lineaDao.borrar(linea);
		
	}

	@Override
	public TreeMap<String, Linea> buscarTodos() {
		return lineaDao.buscarTodos();
		
	}

}
