
package modelo;


public class MisionTripulada extends Mision{
        private static final long serialVersionUID = 1L;
        
        private int cantidadAstronautas;
        
        public MisionTripulada(int id, String nombre, String destino, double presupuesto, int duracionEstimada, Agencia agencia, int cantidadAstronautas){
            super(id, nombre, destino, presupuesto, duracionEstimada, agencia);
            this.cantidadAstronautas = cantidadAstronautas;
        }

    public int getCantidadAstronautas() {
        return cantidadAstronautas;
    }
        @Override
        public String detalle(){
            return "Tripulada:" + cantidadAstronautas + "astrounautas";
        }
}
