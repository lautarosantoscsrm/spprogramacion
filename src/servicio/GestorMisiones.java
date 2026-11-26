
package servicio;
import excepciones.ValidacionException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import modelo.*;
import persistencia.Almacen;

public class GestorMisiones {
    private ArrayList<Mision> misiones = new ArrayList<>();
    private Almacen<ArrayList<Mision>> almacen;

    public GestorMisiones(Almacen<ArrayList<Mision>> almacen) {
        this.almacen = almacen;
    }
    public void cargar () throws IOException, ClassNotFoundException{
        ArrayList<Mision> datos = almacen.cargar();
        if (datos != null){
            misiones = datos;
        }
    }
    public ArrayList<Mision> listar(){
        return new ArrayList<>(misiones);
    }
    private int posicion(int id){
        for(int i = 0; i < misiones.size(); i++){
            if(misiones.get(i).getIdMision() == id){
                return i;
            }
        }
        return -1;
    }
    private void validar(Mision m) throws ValidacionException{
        if(m.getIdMision()<= 0 || m.getNombre().trim().isEmpty() || m.getDestino().trim().isEmpty()||Double.isFinite(m.getPresupuesto())|| m.getPresupuesto()<= 0 || m.getDuracionEstimada() <=0 || m.getAgencia() == null){
        throw new ValidacionException("Completar con valores validos");
    }
        if(m instanceof MisionTripulada && ((MisionTripulada)m).getCantidadAstronautas() <= 0){
            throw new ValidacionException("Debe haber 1 o mas astronautas");
        }
        if ( m instanceof MisionNoTripulada && ((MisionNoTripulada)m).getDispositivo() == null){
            throw new ValidacionException("Debe de haber un dispositivo");
        }
    }
        public void agregar(Mision m) throws ValidacionException, IOException{
            validar(m);
            if(posicion(m.getIdMision()) != -1){
                throw new ValidacionException("Id repetida");
            }
            ArrayList<Mision> copia = listar();
                copia.add(m);
                almacen.guardar(copia);
                misiones = copia;
        }
        public void modificar(Mision m) throws ValidacionException, IOException{
            validar(m);
            int i = posicion(m.getIdMision());
            if(i == -1){
                throw new ValidacionException("No existe esa mision");
            }
            ArrayList<Mision> copia = listar();
                
                copia.set(i, m);
                almacen.guardar(copia);
                misiones = copia;
            
        }
        public void eliminar(Mision m) throws ValidacionException, IOException{
            int i = m == null ? -1: posicion(m.getIdMision());
            if (i == -1){
                throw new ValidacionException("Selecciona una mision");
                
            }
            ArrayList<Mision> copia = listar();
                
                copia.remove(i);
                almacen.guardar(copia);
                misiones = copia;
    }
        public void guardar()throws ValidacionException, IOException{
            almacen.guardar(misiones);
        }
        public double presupuestoTotal(){
            double total =0;
            for(Mision m : misiones){
                total += m.getPresupuesto();
            }
            return total;
        }
}
