package colectivo.servicio;

import java.util.List;

import colectivo.conexion.Factory;
import colectivo.dao.TramoDao;
import colectivo.dao.secuencial.TramoSecuencialDAO;
import colectivo.modelo.Tramo;

public class TramoServiceImpl implements TramoService {

	private TramoDao tramoDao; 
		
	public TramoServiceImpl(){
		tramoDao = (TramoSecuencialDAO) Factory.getInstancia("TRAMO");
	}
	
	@Override
	public void insertar(Tramo tramo) {
		tramoDao.insertar(tramo);				
	}

	@Override
	public void actualizar(Tramo tramo) {
		tramoDao.actualizar(tramo);						
	}

	@Override
	public void borrar(Tramo tramo) {
		tramoDao.borrar(tramo);
		
	}

	@Override
	public List<Tramo> buscarTodos() {
		return tramoDao.buscarTodos();
		
	}

}
