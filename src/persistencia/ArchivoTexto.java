
package persistencia;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import java.io.*;
import java.util.List;
import modelo.*;
public class ArchivoTexto {
    public Agencia[]
            cargarAgencias() throws IOException{
                try (Reader lector = new FileReader("agencias.json")){
                    Agencia[] agencias = new Gson().fromJson(lector,Agencia[].class);
                    
                    if (agencias == null || agencias.length == 0){
                        throw new IOException("El archivo no contiene agencias");
                    }
                    return agencias;
                } catch (JsonParseException e){
                    throw new IOException("El archivo no es valido");
                }
            }
            public void exportar(List<Mision> misiones)throws IOException{
                try(BufferedWriter escritor = new BufferedWriter(new FileWriter("alto_presupuesto.txt"))){
                    for (Mision m : misiones){
                        if(m.getPresupuesto()> 100_000_000){
                        escritor.write(m.toString());
                        escritor.newLine();
                    }
                    }
                }
            }
                    
}
