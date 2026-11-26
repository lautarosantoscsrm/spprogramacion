
package modelo;

import java.io.Serializable;

public abstract class Mision implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private final int idMision;
    private String nombre;
    private String destino;
    private double presupuesto;
    private int duracionEstimada;
    private Agencia agencia;
    
    
    public Mision (int idMision, String nombre, String destino, double presupuesto, int duracionEstimada, Agencia agencia){
        this.idMision = idMision;
        this.nombre = nombre;
        this.destino = destino;
        this.presupuesto = presupuesto;
        this.duracionEstimada = duracionEstimada;
        this.agencia = agencia;
     
    }

    public int getIdMision() {
        return idMision;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDestino() {
        return destino;
    }

    public double getPresupuesto() {
        return presupuesto;
    }

    public int getDuracionEstimada() {
        return duracionEstimada;
    }

    public Agencia getAgencia() {
        return agencia;
    }
    
    public abstract String detalle();
    
    
    @Override

    public String toString() {
        return "Mision{" + "idMision=" + idMision + ", nombre=" + nombre + ", destino=" + destino + ", presupuesto=" + presupuesto + ", duracionEstimada=" + duracionEstimada + ", agencia=" + agencia + '}' + detalle();
    }
        
        
    
}
