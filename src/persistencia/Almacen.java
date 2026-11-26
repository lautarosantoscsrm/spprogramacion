
package persistencia;

import java.io.IOException;

public interface Almacen <T>{
    T cargar() throws IOException, ClassNotFoundException;
    void guardar (T datos) throws IOException;
    
}
