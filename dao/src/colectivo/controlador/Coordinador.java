package colectivo.controlador;

import java.util.List;

import colectivo.interfaz.Interfaz;
import colectivo.modelo.Linea;
import colectivo.modelo.Parada;
import colectivo.modelo.Tramo;
import colectivo.negocio.Calculo;
import colectivo.negocio.Empresa;
import net.datastructures.TreeMap;

public class Coordinador {

    private Empresa empresa;
    private Calculo calculo;
    private Interfaz interfaz;

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Calculo getCalculo() {
        return calculo;
    }

    public void setCalculo(Calculo calculo) {
        this.calculo = calculo;
    }

    public Interfaz getInterfaz() {
        return interfaz;
    }

    public void setInterfaz(Interfaz interfaz) {
        this.interfaz = interfaz;
    }

    // Interfaz

    // LineaList

    // Empresa

    public Linea buscarLinea(Linea linea) {
        return empresa.buscarLinea(linea);
    }

    public TreeMap<String, Linea> listarLineas() {
        return empresa.getLineas();
    }

    public TreeMap<String, Parada> listarParadas() {
        return empresa.getParadas();
    }

    public List<Tramo> listarTramos() {
        return empresa.getTramos();
    }

}
