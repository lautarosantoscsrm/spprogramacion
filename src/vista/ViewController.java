
package vista;
import excepciones.ValidacionException;
import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import modelo.*;
import persistencia.*;
import servicio.GestorMisiones;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;


public class ViewController implements Initializable {

    @FXML private TextField txtId, txtNombre, txtDestino;
    
    @FXML private TextField txtPresupuesto, txtDuracion, txtAstronautas;
    
    @FXML private ComboBox<Agencia> cmbAgencia;
    @FXML private ComboBox<String> cmbTipo;
    @FXML private ComboBox<TipoDispositivo> cmbDispositivo;
    @FXML private ComboBox<Mision> cmbMision;
    @FXML private TextArea txtListado;
    @FXML private Label lblMensaje;
    
    private GestorMisiones gestor;
    private ArchivoTexto archivos = new ArchivoTexto();
    private boolean listo;
   
    
   
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbDispositivo.getItems().addAll(TipoDispositivo.values());
        
        gestor = new GestorMisiones(new ArchivoBinario<ArrayList<Mision>>("misiones.bin"));
            try{
                cmbAgencia.getItems().addAll(archivos.cargarAgencias());
                gestor.cargar();
                listo = true;
                listar();
                nuevo();
                
               lblMensaje.setText("Sistema listo");
               
            } catch (IOException | ClassNotFoundException e){
                lblMensaje.setText("Error al cargar" + e.getMessage()); 
            }
            
    }    
    private boolean disponible(){
        if(!listo){
            lblMensaje.setText("Error a corregir");
        } return listo;
    }
    @FXML private void nuevo(){
        cmbMision.setValue(null);
        txtId.setEditable(true);
        
        txtId.clear();
        txtNombre.clear();
        txtDestino.clear();
        txtPresupuesto.clear();
        txtDuracion.clear();
        txtAstronautas.clear();
        
        cmbAgencia.setValue(null);
        cmbDispositivo.setValue(null);
        cmbTipo.setValue("Tripulada");
            cambiarTipo();
    }
    
    @FXML private void cambiarTipo(){
        boolean tripulada = "Tripulada".equals(cmbTipo.getValue());
        
        txtAstronautas.setDisable(tripulada);
        cmbDispositivo.setDisable(tripulada);
    }
    @FXML private void seleccionar(){
        Mision m =  cmbMision.getValue();
            if(m == null)
                return;
            txtId.setText(String.valueOf(m.getIdMision()));
                txtId.setEditable(false);
                
                txtNombre.setText(m.getNombre());
                txtDestino.setText(m.getDestino());
                txtPresupuesto.setText(String.valueOf(m.getPresupuesto()));
                txtDuracion.setText(String.valueOf(m.getDuracionEstimada()));
                    cmbAgencia.setValue(null);
                    
                    for(Agencia a : cmbAgencia().getId()){
                        cmbAgencia.setValue(a);
                            break;
                    }  
      } 
                if(m instanceof MisionTripulada){
                    MisionTripulada t = (MisionTripulada) m;
                    cmbTipo.setValue("Tripulada");
                    txtAstronautas.setText(String.valueOf(t.getCantidadAstronautas()));
                    cmbDispositivo.setValue(null);
                }
                else{
                    MisionNoTripulada n = (MisionNoTripulada) m;
                        cmbTipo.setValue("No tripulada");
                        cmbDispositivo.setValue(n.getDispositivo());
                        txtAstronautas.clear();
    
    
}
                private Mision formulario(int id) throws ValidacionException{
                    try{
                        String nombre= txtNombre.getText().trim();
                        String destino= txtDestino.getText().trim();
                        double presupuesto= Double.parseDouble(txtPresupuesto.getText().trim().replace( " .", " "));
                        int dias = Integer.parseInt(txtDuracion.getText().trim());
                        Agencia agencia= cmbAgencia.getValue();
                            if("Tripulada".equals(cmbTipo.getValue())){
                                int astronautas = Integer.parseInt(txtAstronautas.getText().trim());
                                    return new MisionTripulada( id, nombre, destino, presupuesto, dias, agencia, astronautas);
                            }
                            if("No tripulada".equals(cmbTipo.getValue())){
                                return new MisionNoTripulada(id, nombre, destino, presupuesto, dias, agencia, cmbDispositivo.getValue());
                            }
                            throw new ValidacionException("Elegir tipo de mision");
                    } catch(NumberFormatException e){
                        throw new ValidacionException("Revisar campos numericos");
                    }
                        
                    
                } @FXML private void agregar(){
                    if (!disponible()) return;
                    try{
                        int id= Integer.parseInt(txtId.getText().trim());
                        gestor.agregar(formulario(id));
                           listar();
                           nuevo();
                       lblMensaje.setText("La mision se agrego y guardo");
                       
                    } catch(NumberFormatException e){
                        lblMensaje.setText("La id debe ser un numero entero");
                    } catch(ValidacionException | IOException e){
                        lblMensaje.setText(e.getMessage());
                    }
                } @FXML private void modificar(){
                    if(!disponible()) return;
                    try{
                        Mision m = cmbMision.getValue();
                        
                        if(m == null){
                            throw new ValidacionException("Elija una mision");
                        } gestor.modificar(formulario(m.getIdMision()));
                        listar();
                        nuevo();
                        lblMensaje.setText("Mision modificada y guardada");
                    } catch(ValidacionException | IOException e){
                        lblMensaje.setText(e.getMessage());
                    }
                }
                
                @FXML private void elimiar(){
                     if (!disponible()) return;
                     try{
                         Mision m = cmbMision.getValue();
                        if(m == null){
                            throw new ValidacionException("Seleccione una mision");
                        } Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
                        aviso.setHeaderText("Desea eliminar esta mision?");
                        aviso.setContentText(m.toString());
                            if(aviso.showAndWait().orElse(ButtonType.CANCEL)!= ButtonType.OK){
                                return;
                            }
                            gestor.eliminar(m);
                            listar();
                            nuevo();
                            lblMensaje.setText(e.getMessage());
                            
                         
                     }
                    
                       
                    
                          }
                @FXML private void listar(){
                    if(!disponible()) return;
                    cmbMision.getItems().setAll(gestor.listar());
                    StringBuilder texto = new StringBuilder();
                    for(Mision m : gestor.listar()){
                        texto.append(m).append("\n\n");
                    }
                    txtListado.setText(texto.length() == 0 ? "No existen misiones registradas" : texto.toString());
                }
               @FXML public void guardar(){
                   if (!disponible()) return;
                     try{
                         gestor.guardar();
                         lblMensaje.setText("Misiones guardadas");
                         
                     } catch(IOException e){
                         lblMensaje.setText(e.getMessage());
                     }
               }
                
                
                
    
     
    }
