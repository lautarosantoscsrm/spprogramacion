
package modelo;

import java.io.Serializable;

public class Agencia implements Serializable {
    
    private static final long serialVersionUID = 1L;
        

    private int id;
    private String nombre;
    private String pais;
    private int anioFundacion;
    private String email;
   
    public Agencia(){
        
    }
    
    public int getId(){
        return id;
    }
    public String getNombre(){
        return nombre;
    }
    public String getPais(){
        return pais;
    }
    public int getAnioFundacion(){
        return anioFundacion;
    }
    public String getEmail(){
        return email;
    }
    
    @Override
        public String toString(){
            return nombre + " - " + pais;
        }
    
 
}
