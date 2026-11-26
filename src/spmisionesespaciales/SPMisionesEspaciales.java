
package spmisionesespaciales;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;




public class SPMisionesEspaciales extends Application{
    @Override
        public void start(Stage stage) throws Exception{
            Parent root = FXMLLoader.load(getClass().getResource("/vista/view.fxml"));
                stage.setScene(new Scene(root));
                stage.setTitle("Mision espacial");
                stage.show();
        }

   
    public static void main(String[] args) {
        
        
    }
    
}
