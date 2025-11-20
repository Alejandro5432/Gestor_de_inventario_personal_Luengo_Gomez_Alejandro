package es.ies.claudiomoyano.dam2.pmdm.gestor_de_inventario_personal_luengo_gomez_alejandro;

import java.io.Serializable;
import java.time.LocalDate;
public class Pertenencia implements Serializable {
    private String nombrePertencia;
    private String categoria; // Electrónica, mueble, etc...
    private int unidades;
    private double pesoUnidad; // En kilogramos
    private String dimensiones;
    private boolean fragil;
    private double valorUnidad;
    private LocalDate fechaCompra; // Atributo opcional

    /**
     * Constructor de la clase Pertenencia que inicializa todas las propiedades excepto las relacionadas con el precio o la fecha de su adquisición.
     * @param nombrePertencia Nombre del bien en concreto.
     * @param categoria El tipo del objeto.
     * @param unidades La cantidad dispuesta de este recurso.
     * @param pesoUnidad La masa de cada individuo.
     * @param dimensiones Tamaño aproximado del enser.
     * @param fragil Si la pertenencia en cuestión debe manejarse con cautela.
     */
    public Pertenencia(String nombrePertencia, String categoria, int unidades, double pesoUnidad, String dimensiones, boolean fragil) {
        this.nombrePertencia = nombrePertencia;
        this.categoria = categoria;
        this.unidades = unidades;
        this.pesoUnidad = pesoUnidad;
        this.dimensiones = dimensiones;
        this.fragil = fragil;
    }

    /**
     * Constructor de la clase Pertenencia que inicaliza todas sus propiedades.
     * @param nombrePertencia Nombre del bien en concreto.
     * @param categoria El tipo del objeto.
     * @param unidades La cantidad dispuesta de este recurso.
     * @param pesoUnidad La masa de cada individuo.
     * @param dimensiones Tamaño aproximado del enser.
     * @param fragil Si la pertenencia en cuestión debe manejarse con cautela.
     * @param valorUnidad El precio de la propiedad.
     * @param fechaCompra El momento en el que el bien fue procurado.
     */
    public Pertenencia(String nombrePertencia, String categoria, int unidades, double pesoUnidad, String dimensiones, boolean fragil, double valorUnidad, LocalDate fechaCompra) {
        this.nombrePertencia = nombrePertencia;
        this.categoria = categoria;
        this.unidades = unidades;
        this.pesoUnidad = pesoUnidad;
        this.dimensiones = dimensiones;
        this.fragil = fragil;
        this.valorUnidad = valorUnidad;
        this.fechaCompra = fechaCompra;
    }

    public String getNombrePertencia() {
        return nombrePertencia;
    }

    public void setNombrePertencia(String nombrePertencia) {
        this.nombrePertencia = nombrePertencia;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public double getPesoUnidad() {
        return pesoUnidad;
    }

    public void setPesoUnidad(double pesoUnidad) {
        this.pesoUnidad = pesoUnidad;
    }

    public String getDimensiones() {
        return dimensiones;
    }

    public void setDimensiones(String dimensiones) {
        this.dimensiones = dimensiones;
    }

    public boolean isFragil() {
        return fragil;
    }

    public void setFragil(boolean fragil) {
        this.fragil = fragil;
    }

    public double getValorUnidad() {
        return valorUnidad;
    }

    public void setValorUnidad(double valorUnidad) {
        this.valorUnidad = valorUnidad;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    @Override
    public String toString() {
        return "Pertenencia{" +
                "nombrePertencia='" + nombrePertencia + '\'' +
                ", categoria='" + categoria + '\'' +
                ", unidades=" + unidades +
                ", pesoUnidad=" + pesoUnidad +
                ", dimensiones='" + dimensiones + '\'' +
                ", fragil=" + fragil +
                ", valorUnidad=" + valorUnidad +
                ", fechaCompra=" + fechaCompra +
                '}';
    }
}