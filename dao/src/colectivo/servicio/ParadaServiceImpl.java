package colectivo.servicio;


import net.datastructures.TreeMap;
import colectivo.conexion.Factory;
import colectivo.dao.ParadaDao;
import colectivo.dao.secuencial.ParadaSecuencialDAO;
import colectivo.modelo.Parada;

public class ParadaServiceImpl implements ParadaService {

	private ParadaDao paradaDao; 
		
	public ParadaServiceImpl(){
		paradaDao = (ParadaSecuencialDAO)Factory.getInstancia("PARADA");
	}
	
	@Override
	public void insertar(Parada parada) {
		paradaDao.insertar(parada);				
	}

	@Override
	public void actualizar(Parada parada) {
		paradaDao.actualizar(parada);						
	}

	@Override
	public void borrar(Parada parada) {
		paradaDao.borrar(parada);
		
	}

	@Override
	public TreeMap<String, Parada> buscarTodos() {
		return paradaDao.buscarTodos();
		
	}

}
