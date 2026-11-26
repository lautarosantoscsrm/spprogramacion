
package persistencia;

import java.io.*;

public class ArchivoBinario<T extends Serializable> implements Almacen<T> {
    private String ruta;
    public ArchivoBinario(String ruta){
        this.ruta= ruta;
    }
    @Override
    @SuppressWarnings ("Fallo")
     public T cargar() throws IOException, ClassNotFoundException{
         if (!new File(ruta).exists()){
             return null;
         }
             
         try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(ruta))){
             return (T) 
              entrada.readObject();
         }
     }
     @Override
        public void guardar(T datos) throws IOException{
            try(ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(ruta))){
                salida.writeObject(datos);
            }
                
        }
}
