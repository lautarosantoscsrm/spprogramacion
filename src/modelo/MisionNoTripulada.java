
package modelo;


public class MisionNoTripulada extends Mision {
            private static final long serialVersionUID = 1L;
            
            private TipoDispositivo dispositivo;
            
            public MisionNoTripulada(int id, String nombre, String destino, double presupuesto, int duracionEstimada, Agencia agencia, TipoDispositivo dispositivo){
            super(id, nombre, destino, presupuesto, duracionEstimada, agencia);
            this.dispositivo = dispositivo;
        }

    public TipoDispositivo getDispositivo() {
        return dispositivo;
    }
            @Override
        public String detalle(){
            return "No Tripulada:" +  dispositivo;
        }
    
}
