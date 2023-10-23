package colectivo.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Parada {

	private String id;
	private String direccion;
	private List<Linea> lineas;

	public Parada() {
		this.lineas = new ArrayList<Linea>();
	}

	public Parada(String id, String direccion) {
		super();
		this.id = id;
		this.direccion = direccion;
		this.lineas = new ArrayList<Linea>();
	}

	public void agregarLinea(Linea linea) {
	        this.lineas.add(linea);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public List<Linea> getLineas() {
		if (lineas != null) {
			List<Linea> lineasCopy = new ArrayList<>(lineas);
			return lineasCopy;
		} else {
			return new ArrayList<>(); // Devuelve una lista vacía en lugar de null.
		}
	}

	/**
	 * @param lineas the lineas to set
	 */
	public void setLineas(List<Linea> lineas) {
		this.lineas = lineas;
	}

	@Override
	public int hashCode() {
		return Objects.hash(direccion, id, lineas);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Parada other = (Parada) obj;
		return Objects.equals(direccion, other.direccion) && Objects.equals(id, other.id)
				&& Objects.equals(lineas, other.lineas);
	}

	@Override
	public String toString() {
		return "Parada [id=" + id + ", direccion=" + direccion + "]";
	}

}
