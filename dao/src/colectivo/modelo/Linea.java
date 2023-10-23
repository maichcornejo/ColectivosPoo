package colectivo.modelo;

import java.util.ArrayList;
import java.util.List;

public class Linea {

	private String nombre;
	private List<Parada> paradasIda;
	private List<Parada> paradasRegreso;

	public Linea() {
		this.paradasIda = new ArrayList<Parada>();
		this.paradasRegreso = new ArrayList<Parada>();
	}

	public Linea(String nombre) {
		super();
		this.nombre = nombre;
		this.paradasIda = new ArrayList<Parada>();
		this.paradasRegreso = new ArrayList<Parada>();
	}

	public void agregarParadaIda(Parada parada) {
		paradasIda.add(parada);
		
			parada.agregarLinea(this);
		
	}

	public void agregarParadaRegreso(Parada parada) {
		paradasRegreso.add(parada);
		
			parada.agregarLinea(this);
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Parada> getParadasIda() {
		List<Parada> paradasIdaCopy = new ArrayList<>();
		paradasIdaCopy.addAll(paradasIda);
		return paradasIdaCopy;
	}

	public List<Parada> getParadasRegreso() {
		List<Parada> paradasRegresoCopy = new ArrayList<>();
		paradasRegresoCopy.addAll(paradasRegreso);
		return paradasRegresoCopy;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Linea other = (Linea) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nombre;
	}

}
