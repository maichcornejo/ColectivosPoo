package colectivo.controlador;

import java.util.List;

import colectivo.interfaz.Interfaz;
import colectivo.modelo.Parada;
import colectivo.modelo.Tramo;
import colectivo.negocio.Calculo;
import colectivo.negocio.Empresa;
import colectivo.negocio.LineaExisteException;


public class AplicacionConsultas {

	// l�gica
	private Empresa empresa;
	private Calculo calculo;

	// vista
	private Interfaz interfaz;

	// controlador
	private Coordinador coordinador;

	public static void main(String[] args) throws LineaExisteException {
		AplicacionConsultas miAplicacion = new AplicacionConsultas();
		miAplicacion.iniciar();
		miAplicacion.consultar();
	}

	private void iniciar() throws LineaExisteException {
		/* Se instancian las clases */
		empresa = Empresa.getEmpresa();
		calculo = new Calculo();
		coordinador = new Coordinador();
		interfaz = new Interfaz();

		/* Se establecen las relaciones entre clases */
		calculo.setCoordinador(coordinador);
		interfaz.setCoordinador(coordinador);

		/* Se establecen relaciones con la clase coordinador */
		coordinador.setEmpresa(empresa);
		coordinador.setCalculo(calculo);

		calculo.cargarDatos(coordinador.listarTramos(), coordinador.listarParadas());
	}

	private void consultar() throws LineaExisteException {

		// Ingreso datos usuario
		int opcion = Interfaz.opcion();
		Parada origen = Interfaz.ingresarParadaOrigen(coordinador.listarParadas());
		Parada destino = interfaz.ingresarParadaDestino(coordinador.listarParadas());

		// Realizar calculo
		calculo.cargarDatos(coordinador.listarTramos(), coordinador.listarParadas());

		List<Tramo> recorrido = null;
		if (opcion == Constantes.MAS_RAPIDO)
			recorrido = calculo.rapido(origen, destino);
			System.out.println(recorrido);
		// Mostrar resultado
		interfaz.resultado(recorrido);

	
	}
}
